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

import sessionBean.view.GenerateDataLocal;
import tool.CustomException;

public class DataWebServiceAction extends Action{
	
	
	public ActionForward execute(ActionMapping pMapping, ActionForm pForm, HttpServletRequest pRequete,
			HttpServletResponse pReponse) throws SecurityException, CustomException {
		
		GenerateDataLocal generation = lookupGenerateDataLocal();
		try {
		generation.startWebService();
		pRequete.setAttribute("message", "Démarrage du webService");
		}catch(NullPointerException ex) {
			pRequete.setAttribute("message", "Echec webService");
			
		}
		return pMapping.findForward("succes");
		
	}
	
	   private GenerateDataLocal lookupGenerateDataLocal(){
	        try{
	            Context c = new InitialContext();	  
	            return (GenerateDataLocal) c.lookup("java:global/gestionStock-ear/gestionStock-ejb/GenerateData!sessionBean.view.GenerateDataLocal");
	            
	        }catch (NamingException ne){
	            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
	            throw new RuntimeException(ne);
	            
	        }
	        
	    }	

}
