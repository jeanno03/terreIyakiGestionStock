package action;

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

import entity.User;
import sessionBean.view.ProductTreatmentLocal;

public class ProductGererModifierProductComposantAction extends Action {

	public ActionForward execute(ActionMapping pMapping, ActionForm pForm, HttpServletRequest pRequete,
			HttpServletResponse pReponse) throws SecurityException {

		HttpSession pSession = pRequete.getSession();
		User myUser = (User) pSession.getAttribute("myUser");

		ProductTreatmentLocal productTreatment = lookupProductTreatmentLocal();

		/** Récupération des informations du formulaire **/
		DynaValidatorActionForm lForm = (DynaValidatorActionForm) pForm;

		String message = null;
		int num = 0;
		int amount = 0;
		Long id = 0L;
		String statusComposant = lForm.getString("statusComposant");
		String nameComposant = lForm.getString("nameComposant");
		String terreIyakiProduct = lForm.getString("terreIyakiProductComposant");
		String freshProduct = lForm.getString("freshProductComposant");

		try {
			id = Long.valueOf(lForm.getString("idComposant"));
		} catch (NumberFormatException ex) {
			//
		}
		try {
			amount = Integer.valueOf(lForm.getString("amountComposant"));
		} catch (NumberFormatException ex) {
			//
		}
		try {
			num = Integer.valueOf(lForm.getString("numComposant"));
		} catch (NumberFormatException ex) {
			//
		}

		//
		// System.out.println("terreIyakiProductComposant terreIyakiProductComposant
		// terreIyakiProductComposant "+terreIyakiProduct);
		// System.out.println("idComposant idComposant idComposant "+id);
		// System.out.println("statusComposant statusComposant statusComposant
		// "+statusComposant);

		message = productTreatment.activateProductComposant(id, statusComposant, myUser);

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
