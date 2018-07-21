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

import entity.FreshProduct;
import entityTerreIyaki.TerreIyakiProduct;
import sessionBean.view.ProductTreatmentLocal;

public class ProductAfficherFreshProductAction extends Action {

	public ActionForward execute(ActionMapping pMapping, ActionForm pForm, HttpServletRequest pRequete,
			HttpServletResponse pReponse) throws SecurityException {

		ProductTreatmentLocal productTreatment = lookupProductTreatmentLocal();

		List<FreshProduct> fe01 = productTreatment.getFreshProductList();
		pRequete.setAttribute("FreshProduct", fe01);

		List<TerreIyakiProduct> te01 = productTreatment.getTerreIyakiProductList();
		pRequete.setAttribute("TerreIyakiProduct", te01);
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
