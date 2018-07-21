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

import entity.FreshProduct;
import entity.ProductComposant;
import entityTerreIyaki.TerreIyakiProduct;
import sessionBean.view.ProductTreatmentLocal;
import tool.CustomException;

public class ProductAfficherFreshProductByIdAction extends Action {

	public ActionForward execute(ActionMapping pMapping, ActionForm pForm, HttpServletRequest pRequete,
			HttpServletResponse pReponse) throws SecurityException, CustomException {

		String message02 = null;
		String message03 = null;

		ProductTreatmentLocal productTreatment = lookupProductTreatmentLocal();

		/** Récupération des informations du formulaire **/
		DynaValidatorActionForm lForm = (DynaValidatorActionForm) pForm;
		Long id = Long.valueOf(lForm.getString("id"));

		List<FreshProduct> fe01 = productTreatment.getProductComposantListByTerreIyakiProduct(id);
		TerreIyakiProduct te01 = productTreatment.getTerreIyakiProductById(id);
//		
//		if (fe01.isEmpty()) {
//			message02 = "produit frais vide";
//		} else {
			
			message02 = te01.getNamePointeur();
			
			pRequete.setAttribute("FreshProductById", fe01);
			

				
//		}
		
		List<ProductComposant>pc01= productTreatment.getProductComposantByTerreIyakiProduct(id);
		
//		if (pc01.isEmpty()) {
//			message03 = "composant vide";
//		}
//		else {
//			message03 = "Composant pour "+te01.getNamePointeur() ;
			pRequete.setAttribute("ProductComposantById", pc01);
//		}

		pRequete.setAttribute("message02", message02);
//		pRequete.setAttribute("message03", message03);
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
