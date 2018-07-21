package action;

import java.util.logging.Level;
import java.util.logging.Logger;

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

public class UserModifierAction extends Action {

	public ActionForward execute(ActionMapping pMapping, ActionForm pForm, HttpServletRequest pRequete,
			HttpServletResponse pReponse) throws SecurityException, CustomException {

		UserTreatmentLocal myTreatment = lookupUserTreatmentLocal();
		
		DynaValidatorActionForm lForm = (DynaValidatorActionForm) pForm;
		String login = lForm.getString("login");
		String grant = lForm.getString("grant");
		
		String email = lForm.getString("email");
		String firstName = lForm.getString("firstName");
		String lastName = lForm.getString("lastName");
		String message = null;
		
		/** vérification si données non identiques dans la base **/
		if(myTreatment.testModifyUser(login, grant, email, firstName, lastName)) {
			message="aucune modification enregistré";
		}
		

		if (message == null) {
			

			message = myTreatment.modifyUser(login, grant, email, firstName, lastName);

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
