package action;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.mail.AuthenticationFailedException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.DynaValidatorActionForm;

import sessionBean.view.UserTreatmentLocal;
import tool.CustomException;

public class UserModifierMdpAction extends Action {

	public ActionForward execute(ActionMapping pMapping, ActionForm pForm, HttpServletRequest pRequete,
			HttpServletResponse pReponse) throws SecurityException, CustomException, AuthenticationFailedException {

		UserTreatmentLocal myTreatment = lookupUserTreatmentLocal();
		String message = null;
		/** Récupération des informations du formulaire **/
		DynaValidatorActionForm lForm = (DynaValidatorActionForm) pForm;
		String login = lForm.getString("login");
		String password = lForm.getString("mdp");
		String password02 = lForm.getString("mdp02");

		/** vérification si mdp similaires **/
		if (!password.equals(password02)) {
			message = "les mots de passe doivent correspondre";

		}


		/**
		 * si condition ok persistence dans la base
		 **/
		if (message == null) {
			message = myTreatment.modifyPassword(login, password);

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
