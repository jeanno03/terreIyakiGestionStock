package sessionBean;

import java.awt.print.Book;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.Local;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.mindrot.jbcrypt.BCrypt;

import entity.ProductComposant;
import entity.User;
import entityTerreIyaki.TerreIyakiOrderItem;
import entityTerreIyaki.TerreIyakiProduct;
import sessionBean.view.MyTreatmentLocal;
import tool.CustomException;

/**
 * Session Bean implementation class MyTreatment
 */
@Stateless
@Local(MyTreatmentLocal.class)
@LocalBean
public class MyTreatment implements MyTreatmentLocal {

	@PersistenceContext(unitName = "gestionPersistence")
	private EntityManager em;

	/**
	 * Default constructor.
	 */
	public MyTreatment() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public User toConnect(String login, String password) throws SecurityException {

		String passwordOriginal = null;

		/*
		 * recupere l'agent par em.find
		 */
		try {
			User us01 = em.find(User.class, login);
			passwordOriginal = us01.getPassword();

			/*
			 * test mdp en clair avec mdp haché/salé return un boolean
			 */
			if (BCrypt.checkpw(password, passwordOriginal)) {

				// System.out.println("normalement ca doit etre null ici :"+us01);
				return us01;
			}
		} catch (NullPointerException ex) {
			//
		}

		return null;

	}

	//les 15 dernières lignes de commande du restaurant
	@Override
	public List<TerreIyakiOrderItem> getTerreIyakiOrderItemList() throws SecurityException {

		TypedQuery<TerreIyakiOrderItem> qr = em.createNamedQuery(
				"entityTerreIyaki.TerreIyakiOrderItem.getTerreIyakiOrderItemList", TerreIyakiOrderItem.class);

		try {
			List<TerreIyakiOrderItem> te01 = (List<TerreIyakiOrderItem>) qr.setMaxResults(15).getResultList();
			List<TerreIyakiOrderItem> li02 = getTerreIyakiOrderItemRealList(te01);
			
			//test
			
			for (int i=0; i<li02.size();i++) {
				
				String name = li02.get(i).getTerreIyakiProduct().getNamePointeur();
				
				List<ProductComposant> li03 = getLastProductComposantSold(1,name);
			
				
				
				
				System.out.println("name name name : "+name);
				
				System.out.println("liste de composant : "+li03.toString());
				
			}
			
			return li02;
			

		} catch (NullPointerException ex) {
			return null;
		}

	}

	/*
	 * Méthode qui va récupérer liste TerreIyakiOrderItem Va retourner liste avec
	 * les produits sans les menus
	 */
	private List<TerreIyakiOrderItem> getTerreIyakiOrderItemRealList(List<TerreIyakiOrderItem> li01) {

		List<TerreIyakiOrderItem> li02 = new ArrayList();

		for (int i = 0; i < li01.size(); i++) {

			if (li01.get(i).getTerreIyakiProduct() != null) {
				li02.add(li01.get(i));
			}
		}

		return li02;

	}
	@Override
	public List<ProductComposant> getLastProductComposantSold(int num, String name ){
		
		TypedQuery<ProductComposant> qr = em.createNamedQuery(
				"entity.ProductComposant.getProductComposantListByTerreIyakiOrderItem", ProductComposant.class);
		qr.setParameter("paramName", name);
		qr.setParameter("paramNumStatus", num);
		

		try {
			List<ProductComposant> li01 = qr.getResultList();
			return li01;
		}catch(NullPointerException ex) {
			return null;
		}
		
	}
	
//	@NamedQuery(name="entity.ProductComposant.getProductComposantListByTerreIyakiOrderItem",
//	query = "Select p from ProductComposant p join p.terreIyakiProduct t where t.namePointeur = :paramName")
//	
}
