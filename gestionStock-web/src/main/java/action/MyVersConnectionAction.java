package action;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


public class MyVersConnectionAction extends Action{
	
	
	public ActionForward execute(ActionMapping pMapping, ActionForm pForm, HttpServletRequest pRequete,
			HttpServletResponse pReponse)  throws SecurityException {
		


		String message = null;
		message = "Veuillez vous authentifier pour accéder à l'application";

		pRequete.setAttribute("message", message);
		return pMapping.findForward("succes");
		
		
	}


		

}