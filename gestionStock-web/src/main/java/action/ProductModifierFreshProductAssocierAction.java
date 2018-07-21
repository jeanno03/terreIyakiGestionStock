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

import sessionBean.view.ProductTreatmentLocal;

public class ProductModifierFreshProductAssocierAction extends Action {

	public ActionForward execute(ActionMapping pMapping, ActionForm pForm, HttpServletRequest pRequete,
			HttpServletResponse pReponse) throws SecurityException {

		ProductTreatmentLocal productTreatment = lookupProductTreatmentLocal();

		String message = null;

		/** Récupération des informations du formulaire **/

		DynaValidatorActionForm lForm = (DynaValidatorActionForm) pForm;
		String name = lForm.getString("name");
		String terreIyakiProduct = lForm.getString("terreIyakiProduct");
		
		
		//<form-property name="name" type="java.lang.String" />
		//<form-property name="description"
//			type="java.lang.String" />
		//<form-property name="terreIyakiProduct" type="java.lang.String" />	
		//	
		
//		System.out.println("\nname : "+ name);
//		
//		System.out.println("\nterreIyakiProduct : "+ terreIyakiProduct);
		

		message = productTreatment.modifyFreshProduct(name, terreIyakiProduct);
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

