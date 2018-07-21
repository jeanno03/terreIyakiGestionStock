package event;

import java.sql.SQLException;
import java.util.List;
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

import entityTerreIyaki.TerreIyakiProduct;
import sessionBean.view.ProductTreatmentLocal;
import tool.CustomException;
import tool.MaPagination;

public class ProductAfficherEvent extends Action{
	
	public ActionForward execute(ActionMapping pMapping, ActionForm pForm, HttpServletRequest pRequete,
			HttpServletResponse pReponse)  throws SecurityException, CustomException, SQLException {
		
		HttpSession pSession = pRequete.getSession();
		
		String message=null;
		
		ProductTreatmentLocal productTreatment = lookupProductTreatmentLocal();
		
		
		
		/** Récupération des informations du formulaire **/
		DynaValidatorActionForm lForm = (DynaValidatorActionForm) pForm;

		int debut = Integer.valueOf(lForm.getString("debut"));
		
		
		if(debut!=1) {
			pSession.setAttribute("debutSession", debut);
		}
			
		

//			String debutSessionString = (String) pSession.getAttribute("debutSession");
//			
//			if(debutSessionString!=null) {
//				debut = (Integer) pSession.getAttribute("debutSession");
//				
//				
//			}

	//	pSession.setAttribute("debutSession", debut);
		List<MaPagination> li01 = productTreatment.getMaPaginationList();
		pRequete.setAttribute("MaPagination", li01);
		
		try {			
		List<TerreIyakiProduct> te01 = productTreatment.getTerreIyakiProductList(debut);	
		pRequete.setAttribute("TerreIyakiProduct", te01);
		message="Produits du restaurant";
		pRequete.setAttribute("debut02",debut);
		}catch(NullPointerException ex) {

		}		
		
		
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
