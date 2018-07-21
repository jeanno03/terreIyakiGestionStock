package sessionBean;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.ejb.Local;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.mindrot.jbcrypt.BCrypt;

import entity.*;
import entityTerreIyaki.*;
import sessionBean.view.GenerateDataLocal;

/**
 * Session Bean implementation class GenerateData
 */
@Stateless
@Local(GenerateDataLocal.class)
@LocalBean
public class GenerateData implements GenerateDataLocal {

	@PersistenceContext(unitName = "gestionPersistence")
	private EntityManager em;

	/**
	 * Default constructor. 
	 */
	public GenerateData() {
		// TODO Auto-generated constructor stub
	}



	public void getData() {
		
		
		String mdp ="1234";
        // Hacher un mot de passe
        String hashedMdp = BCrypt.hashpw(mdp, BCrypt.gensalt());

		User agent01 = new User("1234",hashedMdp,"jean.paul@gmail.com","jean","paul");
		User agent02 = new User("er9923",hashedMdp,"sam.meva@gmail.com","sam","meva");
		User agent03 = new User("yu4423",hashedMdp,"miche.leon@gmail.com","miche","leon");

		GrantUser grant01 = new GrantUser("user");
		GrantUser grant02 = new GrantUser("super user");
		GrantUser grant03 = new GrantUser("admin");
		
		Status st01 = new Status(0,"inactif");
		Status st02 = new Status(1,"actif");

		agent01.setGrantUser(grant01);
		agent02.setGrantUser(grant02);
		agent03.setGrantUser(grant03);
		agent01.setStatus(st02);
		agent02.setStatus(st02);
		agent03.setStatus(st01);

Mail mail = new Mail("terreiyaki@gmail.com","Afpa03!!");
		
		Date d01 = new Date();
		
FreshProduct fresh01 = new FreshProduct("saumon","poisson","http");
FreshProduct fresh02 = new FreshProduct("thon","poisson","http");


Location loc01 = new Location(1, 2000);
Location loc02 = new Location(2, 2000);

loc01.setFreshProduct(fresh01);
loc02.setFreshProduct(fresh02);

List <Location> listLocation = new ArrayList();
listLocation.add(loc01);
listLocation.add(loc02);

Fridge fridge01 = new Fridge("mon frigo", 10000);
fridge01.setLocations(listLocation);

ProductComposant pc01 = new ProductComposant(1,150);
ProductComposant pc02 = new ProductComposant(1,200);

pc01.setFreshProduct(fresh01);
pc02.setFreshProduct(fresh02);
pc01.setStatus(st02);
pc01.setStatus(st02);


		em.persist(agent01);
		em.persist(agent02);
		em.persist(agent03);
		em.persist(grant01);
		em.persist(grant02);
		em.persist(grant03);
		em.persist(st01);
		em.persist(st02);
		em.persist(mail);
		em.persist(fresh01);
		em.persist(fresh02);
		em.persist(loc01);
		em.persist(loc02);
		em.persist(fridge01);
		em.persist(pc01);
		em.persist(pc02);

	}

	public void startWebService(){
		
		
	}

}
