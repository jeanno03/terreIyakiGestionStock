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

import sessionBean.view.MyTreatmentLocal;
import tool.CustomException;

public class MyDeconnectionAction extends Action{

	public ActionForward execute(ActionMapping pMapping, ActionForm pForm, HttpServletRequest pRequete,
			HttpServletResponse pReponse)  throws SecurityException, CustomException {
		
		HttpSession pSession = pRequete.getSession();
		
		MyTreatmentLocal myTreatment = lookupMyTreatmentLocal();
		String message = null;
		try {
		pSession.removeAttribute("myUser");
		}catch(NullPointerException ex) {
			//
		}
		message = "merci de votre visite";
		pRequete.setAttribute("message", message);
		return pMapping.findForward("succes");
		
		
	}


		
		private MyTreatmentLocal lookupMyTreatmentLocal() {
			try {
				Context c = new InitialContext();
				return (MyTreatmentLocal) c.lookup(
						"java:global/gestionStock-ear/gestionStock-ejb/MyTreatment!sessionBean.view.MyTreatmentLocal");

			} catch (NamingException ne) {
				Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
				throw new RuntimeException(ne);

			}

		}
}
