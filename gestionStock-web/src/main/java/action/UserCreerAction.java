package action;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.mail.AuthenticationFailedException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.DynaValidatorActionForm;

import entity.User;
import sessionBean.view.UserTreatmentLocal;
import tool.CustomException;

public class UserCreerAction extends Action {

	public ActionForward execute(ActionMapping pMapping, ActionForm pForm, HttpServletRequest pRequete,
			HttpServletResponse pReponse) throws SecurityException, CustomException, AuthenticationFailedException {

		HttpSession pSession = pRequete.getSession();
		
		UserTreatmentLocal myTreatment = lookupUserTreatmentLocal();
		
		User userAdmin = (User) pSession.getAttribute("myUser");

		/** Récupération des informations du formulaire **/
		DynaValidatorActionForm lForm = (DynaValidatorActionForm) pForm;
		String login = lForm.getString("login");
		String grant = lForm.getString("grant");
		String password = lForm.getString("mdp");
		String password02 = lForm.getString("mdp02");
		String email = lForm.getString("email");
		String email02 = lForm.getString("email02");
		String firstName = lForm.getString("firstName");
		String lastName = lForm.getString("lastName");
		String message = null;

		/** vérification si mdp similaires **/
		if (!password.equals(password02)) {
			message = "les mots de passe doivent correspondre";

		}

		/** vérification si emails similaires **/
		else if (!email.equals(email02)) {
			message = "les e-mails doivent correspondre";

		}

		/**
		 * vérification si login et email n'exitent pas dans la base si condition ok
		 * persistence dans la base
		 **/
		else if (message == null) {
			message = myTreatment.createUser(login, grant, password, email, firstName, lastName, userAdmin);
		}

		pRequete.setAttribute("message", message);
		return pMapping.findForward("succes");
	}

	private UserTreatmentLocal lookupUserTreatmentLocal() {
		try {
			Context c = new InitialContext();
			return (UserTreatmentLocal) c.lookup(
					"java:global/gestionStock-ear/gestionStock-ejb/UserTreatment!sessionBean.view.UserTreatmentLocal");

		} catch (NamingException ne) {
			Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
			throw new RuntimeException(ne);

		}

	}

}
