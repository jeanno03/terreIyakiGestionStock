package event;

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

import entity.ProductComposant;
import entityTerreIyaki.TerreIyakiOrderItem;
import sessionBean.view.MyTreatmentLocal;
import tool.CustomException;

public class MyOrderItemAfficherEvent extends Action{
	
	public ActionForward execute(ActionMapping pMapping, ActionForm pForm, HttpServletRequest pRequete,
			HttpServletResponse pReponse)  throws SecurityException, CustomException {
		
	
	MyTreatmentLocal myTreatment = lookupMyTreatmentLocal();
	String message = null;
	 
	
	List<TerreIyakiOrderItem> te01 = myTreatment.getTerreIyakiOrderItemList();
	
	try {
	//on envoi les infos du productComposant de la derniere commande
	String name = te01.get(0).getTerreIyakiProduct().getNamePointeur();
	List<ProductComposant> pc01 = myTreatment.getLastProductComposantSold(1, name);
	
	pRequete.setAttribute("lastProductComposant", pc01);
	}catch(IndexOutOfBoundsException ix) {
		//
	}
	
	pRequete.setAttribute("terreIyakiOrderItem", te01);
	pRequete.setAttribute("message", message);
	pRequete.setAttribute("home", "home");
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




