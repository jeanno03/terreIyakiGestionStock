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
import org.apache.struts.validator.DynaValidatorActionForm;

import entity.ChangeUserStatus;
import sessionBean.view.UserTreatmentLocal;
import tool.CustomException;

public class UserAfficherHistoriqueStatusAction extends Action{

	public ActionForward execute(ActionMapping pMapping, ActionForm pForm, HttpServletRequest pRequete,
			HttpServletResponse pReponse) throws SecurityException, CustomException {

		UserTreatmentLocal myTreatment = lookupUserTreatmentLocal();
		String message = null;
		DynaValidatorActionForm lForm = (DynaValidatorActionForm) pForm;
		String login = lForm.getString("login");

		try {
			List<ChangeUserStatus> cu01 = myTreatment.getChangeUserStatusByUser(login);
			if (cu01.isEmpty()) {
				
				message = "Pas d'historique de changement de status";
			} else {
				message = "Voici l'historique :";
				pRequete.setAttribute("change", cu01);
				pRequete.setAttribute("loginHisto",login);
			}
	//		System.out.println("est ce empty???????? :"+cu01);
		} catch (CustomException ex) {
			message = ex.getMessage();
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

