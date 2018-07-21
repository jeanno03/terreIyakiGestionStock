package action;

import java.util.logging.Level;
import java.util.logging.Logger;

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

public class UserModifierStatusAction extends Action {
	

	public ActionForward execute(ActionMapping pMapping, ActionForm pForm, HttpServletRequest pRequete,
			HttpServletResponse pReponse) throws SecurityException, CustomException {
		
		HttpSession pSession = pRequete.getSession();
		
		UserTreatmentLocal myTreatment = lookupUserTreatmentLocal();
		
		DynaValidatorActionForm lForm = (DynaValidatorActionForm) pForm;
		String login = lForm.getString("login");
		String status = lForm.getString("status");		
		String message=null;
		try {
		User userAdmin = (User) pSession.getAttribute("myUser");
		message = myTreatment.changeStatusUser(userAdmin, login, status);
		}catch(NullPointerException ex) {
			//
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
