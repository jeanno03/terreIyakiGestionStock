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

import sessionBean.view.ProductTreatmentLocal;

public class ProductAssocierProductComposantAction extends Action{
	
	public ActionForward execute(ActionMapping pMapping, ActionForm pForm, HttpServletRequest pRequete,
			HttpServletResponse pReponse) throws SecurityException {
		
		
		ProductTreatmentLocal productTreatment = lookupProductTreatmentLocal();
		
		DynaValidatorActionForm lForm = (DynaValidatorActionForm) pForm;
			
		String message = null;
		int num=0;
		int amount =0;
		Long id=0L;
		
		String statusComposant = lForm.getString("statusComposant");
		String nameComposant = lForm.getString("nameComposant");
		String terreIyakiProduct = lForm.getString("terreIyakiProductComposant");		
		String freshProduct = lForm.getString("freshProductComposant");
	
		try {
		id = Long.valueOf(lForm.getString("idComposant"));
		}catch(NumberFormatException ex) {
		//	
		}
		try {
		amount = Integer.valueOf(lForm.getString("amountComposant"));
		}catch(NumberFormatException ex) {
		//	
		}
		try {
		num = Integer.valueOf(lForm.getString("numComposant"));
		}catch(NumberFormatException ex) {
		//	
		}
		
		
		message= productTreatment.associerProductComposantWithTerreIyaki(id, terreIyakiProduct);
		
		pRequete.setAttribute("message", message);
		
		return pMapping.findForward("succes");
		
		
		

	}

	private ProductTreatmentLocal lookupProductTreatmentLocal() {
		try {
			Context c = new InitialContext();
			return (ProductTreatmentLocal) c.lookup(
					"java:global/gestionStock-ear/gestionStock-ejb/ProductTreatment!sessionBean.view.ProductTreatmentLocal");

		} catch (NamingException ne) {
			Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
			throw new RuntimeException(ne);

		}


	}


}
