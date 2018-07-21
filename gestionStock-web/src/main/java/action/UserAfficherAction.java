package action;

import java.util.List;
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

import entity.GrantUser;
import entity.User;
import sessionBean.view.UserTreatmentLocal;
import tool.CustomException;

public class UserAfficherAction extends Action {

	public ActionForward execute(ActionMapping pMapping, ActionForm pForm, HttpServletRequest pRequete,
			HttpServletResponse pReponse) throws SecurityException, CustomException {

		UserTreatmentLocal myTreatment = lookupUserTreatmentLocal();

		try {
			List<User> ag01 = myTreatment.getUserActifList();
			List<GrantUser> gu01 = myTreatment.getGrantUserList();
			pRequete.setAttribute("user", ag01);
			pRequete.setAttribute("grantUser", gu01);

		} catch (CustomException ex) {
			String text = ex.getMessage();
			pRequete.setAttribute("message", text);

		}

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
