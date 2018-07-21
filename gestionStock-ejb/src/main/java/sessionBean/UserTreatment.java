package sessionBean;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;


import javax.ejb.Local;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.mail.AuthenticationFailedException;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.naming.NamingException;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import org.mindrot.jbcrypt.BCrypt;

import entity.GrantUser;
import entity.Historisation;
import entity.Status;
import entity.ChangeProductStatus;
import entity.ChangeUserStatus;
import entity.User;
import entityTerreIyaki.Mail;
import sessionBean.view.UserTreatmentLocal;
import tool.CustomException;

/**
 * Session Bean implementation class AgentTreatment
 */
@Stateless
@Local(UserTreatmentLocal.class)
@LocalBean
public class UserTreatment implements UserTreatmentLocal {

	@PersistenceContext(unitName = "gestionPersistence")
	private EntityManager em;

	/**
	 * Default constructor.
	 */
	public UserTreatment() {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public List<User> getUserList() throws SecurityException {

		TypedQuery<User> qr = em.createNamedQuery("entity.User.getUserList", User.class);

		try {
			List<User> a01 = (List<User>) qr.getResultList();
			return a01;

		} catch (NullPointerException ex) {
			return null;
		}

	}

	@Override
	public List<User> getUserActifList() throws CustomException, SecurityException {
		TypedQuery<User> qr = em.createNamedQuery("entity.User.getUserActifList", User.class);
		qr.setParameter("paramNumActif", 1);

		try {
			List<User> a01 = (List<User>) qr.getResultList();
			return a01;

		} catch (NoResultException ex) {
			CustomException ce = new CustomException(CustomException.USER_ERR, "liste utilisateur actif vide");

			throw ce;
		}
	}

	@Override
	public String createUser(String login, String grant, String password, String email, String firstName,
			String lastName, User userAdmin) throws SecurityException, AuthenticationFailedException {

		Status statusActif = em.find(Status.class, 1);
		String message = null;
		String messageLogin = null;
		String messageEmail = null;
		String hashedpassword = null;
	
		// Hacher un mot de passe
		hashedpassword = BCrypt.hashpw(password, BCrypt.gensalt());

		User ag01 = new User(login, hashedpassword, email, firstName, lastName);

		//condition 1 : si login ou email existe dans base		 
		try {
			// methode qui retourne tous les agents de la bdd
			List<User> ag02 = getUserList();

			// condition 1 : échec
			for (int i = 0; i < ag02.size(); i++) {
				if (ag02.get(i).getLogin().equals(ag01.getLogin())) {
					messageLogin = "login " + ag01.getLogin() + " non disponible";
				}
			}
			for (int i = 0; i < ag02.size(); i++) {
				if (ag02.get(i).getEmail().equals(ag01.getEmail())) {
					messageEmail = "email " + ag01.getEmail() + " non disponible";
				}
			}

		} catch (NullPointerException ex) {
			//
		}

		//condition 1 : succès	
		if (messageLogin == null && messageEmail == null) {		
			
			//condition 2 : si envoi mail = succès		
			String messageMail = envoyerMail(ag01, password);		
						 
			//condition 2 : échec
			if(messageMail==null) {
				message = "Envoi des informations par mail impossible<br/>Echec enregistrement";	
			}
			
			//condition 1 et 2 : Succès
			else {
				GrantUser gu01 = em.find(GrantUser.class, grant);
				ag01.setGrantUser(gu01);
				ag01.setStatus(statusActif);
				em.persist(ag01);
				String comment = "creation de l'utilisateur";
				historiserUserStatus(userAdmin, ag01, statusActif, comment);	
				
				message = "L'utilisateur " + ag01.getLogin()
				+ " a été créé avec succès<br/>Les identifiants de connexion lui ont été envoyé par mail";
			}
		}

		// condition 1 echec : on retourne disponibilité du login et email choisi
		else {
			if (messageLogin == null) {
				messageLogin = "login " + ag01.getLogin() + " disponible";
			}
			if (messageEmail == null) {
				messageEmail = "email " + ag01.getEmail() + " disponible";
			}
			message = "Enregistrement impossible :<br/>" + messageLogin + "<br/>" + messageEmail;
		}

		return message;
	}

	@Override
	public boolean deleteUser(String login) throws SecurityException, CustomException {
		try {

			/*
			 * recupere l'agent par em.find
			 */
			User ag01 = em.find(User.class, login);

			/*
			 * methode qui retourne tous les agents de la bdd
			 */
			List<User> ag02 = getUserList();

			for (int i = 0; i < ag02.size(); i++) {
				if (ag02.get(i).getLogin().equals(ag01.getLogin())) {
					em.remove(ag01);
					return true;
				}

			}

		} catch (NoResultException ex) {
			CustomException ce = new CustomException(CustomException.USER_ERR, "utilisateur ou liste utilisateur vide");

			throw ce;
		}

		return false;

	}

	/*
	 * vérification si paramètres représentent au moin 1 changement retourne un
	 * boolean
	 */
	@Override
	public boolean testModifyUser(String login, String grant, String email, String firstName, String lastName)
			throws CustomException, SecurityException {

		String lo01 = null;
		String gr01 = null;
		String em01 = null;
		String fi01 = null;
		String la01 = null;

		/*
		 * recupere l'agent par em.find
		 */
		try {
			User ag01 = em.find(User.class, login);
			GrantUser grant01 = ag01.getGrantUser();
			lo01 = ag01.getLogin();
			gr01 = grant01.getName();
			em01 = ag01.getEmail();
			fi01 = ag01.getFirstName();
			la01 = ag01.getLastName();

		} catch (NoResultException ex) {
			CustomException ce = new CustomException(CustomException.USER_ERR, "utilisateur inexistant");

			throw ce;
		}

		if (login.equals(lo01) && grant.equals(gr01) && email.equals(em01) && firstName.equals(fi01)
				&& lastName.equals(la01)) {
			return true;
		}
		return false;
	}

	@Override
	public String modifyUser(String login, String grant, String email, String firstName, String lastName)
			throws SecurityException, CustomException {

		User ag01 = em.find(User.class, login);
		ag01.setEmail(email);
		ag01.setFirstName(firstName);
		ag01.setLastName(lastName);

		GrantUser gu01 = em.find(GrantUser.class, grant);
		ag01.setGrantUser(gu01);

		em.merge(ag01);

		String message = "l'utilisateur " + ag01.getLogin() + " a été modifié avec succès";
		return message;

	}

	@Override
	public String modifyPassword(String login, String password) throws SecurityException, AuthenticationFailedException {
		User ag01 = em.find(User.class, login);
		String passwordOriginal = ag01.getPassword();
		String hashedpassword = BCrypt.hashpw(password, BCrypt.gensalt());
		String message = null;
		String messageEmail = null;
		/*
		 * test mdp en clair avec mdp haché/salé return un boolean
		 */
		if (BCrypt.checkpw(password, passwordOriginal)) {
			message = "le mot ne passe doit être différent pour pouvoir être changé";
		} else {
			
			/*
			 * condition 2 : envoi mail OK
			 */
			messageEmail = envoyerMail(ag01, password);
			if (messageEmail==null) {
				message = "Envoi des informations par mail impossible<br/>Echec enregistrement";
			}
			else {
				ag01.setPassword(hashedpassword);
				em.merge(ag01);	
				message = "le mot de passe a été mis a jour, ces données ont été envoyées par mail à l'utilisateur";				
			}

		}
		return message;

	}

	@Override
	public List<GrantUser> getGrantUserList() throws CustomException, SecurityException {
		List<GrantUser> g01;
		TypedQuery<GrantUser> qr = em.createNamedQuery("entity.GrantUser.getGrantUserList", GrantUser.class);

		try {
			g01 = (List<GrantUser>) qr.getResultList();
			return g01;

		} catch (NoResultException ex) {
			CustomException ce = new CustomException(CustomException.USER_ERR, "liste doit utilisateur vide");
			throw ce;
		}

	}

	@Override
	public List<Status> getStatusList() throws CustomException, SecurityException {
		List<Status> s01;
		TypedQuery<Status> qr = em.createNamedQuery("entity.Status.getStatusList", Status.class);

		try {
			s01 = (List<Status>) qr.getResultList();
			return s01;

		} catch (NoResultException ex) {
			CustomException ce = new CustomException(CustomException.USER_ERR, "liste status vide");
			throw ce;
		}

	}

	@Override
	public String changeStatusUser(User userAdmin, String login, String statusName) throws CustomException {
		String message = null;
		User us01 = em.find(User.class, login);

		TypedQuery<Status> qr = em.createNamedQuery("entity.Status.getStatusbyName", Status.class);
		qr.setParameter("paramName", statusName);
		try {
			Status st01 = qr.getSingleResult();
			/*
			 * Test si le status a changé
			 */
			if (us01.getStatus().equals(st01)) {
				message = "modification impossible le status choisi est le même";
			} else {
				us01.setStatus(st01);
				em.merge(us01);
				message = "le status a été mis a jour";

				/*
				 * on historise cette action via méthode en private
				 */
				String comment = "modification de l'utilisateur";
				historiserUserStatus(userAdmin, us01, st01, comment);
			}

		} catch (NoResultException ex) {
			CustomException ce = new CustomException(CustomException.USER_ERR, "status inexistant");
			throw ce;
		}

		return message;

	}

	/*
	 * on historise cette action
	 */
	private void historiserUserStatus(User userAdmin, User us01, Status st01, String comment) {
		
		Date d01 = new Date();

		// User us01 celui dont le status sera modifier
		// ChangeUserStatus contient l'utilisateur concerné par le changement de status
		ChangeUserStatus cu01 = new ChangeUserStatus(comment);
		cu01.setUser(us01);
		cu01.setStatus(st01);
		
		// Historisation contient l'utilisateur qui a effectué le changement
		Historisation hi01 = new Historisation(d01);
		hi01.setChangeUserStatus(cu01);
		hi01.setUser(userAdmin);

		em.persist(cu01);
		em.persist(hi01);
	}

	
	public List<ChangeUserStatus> getChangeUserStatusByUser(String login) throws CustomException {

		List<ChangeUserStatus> cu01;

		TypedQuery<ChangeUserStatus> qr = em.createNamedQuery("entity.getChangeUserStatusByUser",
				ChangeUserStatus.class);
		qr.setParameter("paramId", login);
		try {
			cu01 = qr.getResultList();

		} catch (NoResultException ex) {
			CustomException ce = new CustomException(CustomException.USER_ERR, "pas d'historique");
			throw ce;
		}
		return cu01;

	}


	
	private String envoyerMail(User us01, String userMdp) throws AuthenticationFailedException {
		
		String messageEmail=null;
		String identifiant = "terreiyaki@gmail.com";
		Mail mail01 = em.find(Mail.class, identifiant);

		// Recipient's email ID needs to be mentioned.
		String mailDestinataire = us01.getEmail();// change accordingly
		String login = us01.getLogin();
		String nom = us01.getLastName();
		String prenom = us01.getFirstName();

		String from = mail01.getMail();// mettre adresse mail
		final String username = mail01.getMail();// mettre adresse mail
		final String password = mail01.getMdp();// mettre mot de passe

		String host = "smtp.gmail.com";

		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.port", "587");

		// Get the Session object.
		Session session = Session.getInstance(props, new javax.mail.Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});

		try {
			// Create a default MimeMessage object.
			Message message = new MimeMessage(session);

			// Set From: header field of the header.
			message.setFrom(new InternetAddress(from));

			// Set To: header field of the header.
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(mailDestinataire));

			// Set Subject: header field
			message.setSubject("Vos identitifants de connection");

			// Now set the actual message
			message.setText("" + nom + " " + prenom + ", votre compte a été créé\n"
					+ "Vous povez vous connecter sur http://jeannory.dynamic-dns.net:8080/gestionStock-web/\n"
					+ "Vos identifiants de connection:\n"
					+ "login :" + login + "\nMdp :" + userMdp+"\n"
					+ "En cas de difficultés, veuillez vous rapprocher d'un administrateur\n\n"
					+ "Cordialement");

			// Send message
			Transport.send(message);

			System.out.println("Sent message successfully....");
			
			messageEmail="mail envoyé";

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		} catch (RuntimeException ex) {
			//refus de connecion google
		}
		
		return messageEmail;

	}

}
