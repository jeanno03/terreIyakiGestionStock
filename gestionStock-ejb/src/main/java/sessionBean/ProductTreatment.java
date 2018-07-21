package sessionBean;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.ejb.Local;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import org.hibernate.*;

import entity.ChangeProductStatus;
import entity.ChangeUserStatus;
import entity.FreshProduct;
import entity.Historisation;
import entity.ProductComposant;
import entity.Status;
import entity.User;
import entityTerreIyaki.TerreIyakiProduct;
import sessionBean.view.ProductTreatmentLocal;
import tool.CustomException;
import tool.MaPagination;

/**
 * Session Bean implementation class ProductTreatment
 */
@Stateless
@Local(ProductTreatmentLocal.class)
@LocalBean
public class ProductTreatment implements ProductTreatmentLocal {

	@PersistenceContext(unitName = "gestionPersistence")
	private EntityManager em;

	/**
	 * Default constructor.
	 */
	public ProductTreatment() {
		// TODO Auto-generated constructor stub
	}

	/*
	 * Calcul le nombre maximum de produits dans la bdd
	 */
	private int getTerreIyakiProductRow() {
		TypedQuery<TerreIyakiProduct> qr = em.createNamedQuery(
				"entityTerreIyaki.TerreIyakiProduct.getTerreIyakiProductList", TerreIyakiProduct.class);

		try {
			List<TerreIyakiProduct> fr01 = (List<TerreIyakiProduct>) qr.getResultList();
			int nb = fr01.size();
			return nb;

		} catch (NullPointerException ex) {
			return 0;
		}

	}

	/*
	 * On fixe à 5 le nombre de produits à afficher par page return le nombre max de
	 * page
	 */

	private int getNbPageTerreIyakiProduct() {
		int maxProduct = getTerreIyakiProductRow();
		int nbpage = 0;
		int moduloNbPage = maxProduct % 5;
		if (moduloNbPage == 0) {
			nbpage = maxProduct / 5;
		} else {
			nbpage = (maxProduct / 5) + 1;
		}
		return nbpage;

	}

	/*
	 * On fixe à 5 le nombre de produits à afficher par page return la liste de
	 * toute les paginations
	 */
	@Override
	public List<MaPagination> getMaPaginationList() {
		try {
			int nbpage = getNbPageTerreIyakiProduct();
			List<MaPagination> li01 = new ArrayList();
			for (int i = 1; i < (nbpage * 5); i++) {

				MaPagination ma01 = getMaPagination(i);

				ma01.setFin(ma01.getFauxFin());
				i = ma01.getFin();
				li01.add(ma01);
			}
			return li01;

		} catch (NullPointerException ex) {
			return null;
		}
	}

	/*
	 * On fixe à 5 le nombre de produits à afficher par page return la valeur de
	 * début et de fin de chaque pagination
	 */
	public MaPagination getMaPagination(int debut) {
		MaPagination ma01 = new MaPagination(debut);
		return ma01;

	}

	@Override
	public List<FreshProduct> getFreshProductList() throws SecurityException {

		TypedQuery<FreshProduct> qr = em.createNamedQuery("entity.FreshProduct.getFreshProductList",
				FreshProduct.class);

		try {
			List<FreshProduct> fr01 = (List<FreshProduct>) qr.getResultList();
			return fr01;

		} catch (NullPointerException ex) {
			//
			return null;
		}

	}

	@Override
	public List<FreshProduct> getProductComposantListByTerreIyakiProduct(Long id) throws SecurityException {

		TypedQuery<FreshProduct> qr = em.createNamedQuery("entity.FreshProduct.getFreshProductListByTerreIyakiProduct",
				FreshProduct.class);
		qr.setParameter("paramId", id);
		try {
			List<FreshProduct> pc01 = (List<FreshProduct>) qr.getResultList();
			return pc01;

		} catch (NullPointerException ex) {
			//
		}
		return null;
	}

	/*
	 * On fixe à 5 le nombre de produits à afficher par page
	 */
	@Override
	public List<TerreIyakiProduct> getTerreIyakiProductList(int debut) throws SecurityException, SQLException {

		try {
			List<TerreIyakiProduct> te01 = (List<TerreIyakiProduct>) em
					.createQuery("Select t from TerreIyakiProduct t", TerreIyakiProduct.class).setMaxResults(5)
					.setFirstResult(debut - 1).getResultList();
			return te01;

		} catch (NullPointerException ex) {
			return null;
		}

	}

	@Override
	public List<TerreIyakiProduct> getTerreIyakiProductList() throws SecurityException {

		TypedQuery<TerreIyakiProduct> qr = em.createNamedQuery(
				"entityTerreIyaki.TerreIyakiProduct.getTerreIyakiProductList", TerreIyakiProduct.class);

		try {
			List<TerreIyakiProduct> te01 = (List<TerreIyakiProduct>) qr.getResultList();
			return te01;

		} catch (NullPointerException ex) {
			return null;
		}

	}

	@Override
	public List<ProductComposant> getProductComposantList() throws SecurityException {

		TypedQuery<ProductComposant> qr = em.createNamedQuery("entity.ProductComposant.getProductComposantList",
				ProductComposant.class);

		try {
			List<ProductComposant> pc01 = (List<ProductComposant>) qr.getResultList();
			return pc01;

		} catch (NullPointerException ex) {
			return null;
		}

	}

	@Override
	public List<ProductComposant> getProductComposantActifList() throws SecurityException {

		TypedQuery<ProductComposant> qr = em.createNamedQuery("entity.ProductComposant.getProductComposantActifList",
				ProductComposant.class);

		try {
			List<ProductComposant> pc01 = (List<ProductComposant>) qr.getResultList();
			return pc01;

		} catch (NullPointerException ex) {
			return null;
		}
	}

	@Override
	public List<ProductComposant> getProductComposantByTerreIyakiProduct(Long id) {

		TypedQuery<ProductComposant> qr = em.createNamedQuery(
				"entity.ProductComposant.getProductComposantByTerreIyakiProduct", ProductComposant.class);
		qr.setParameter("paramIdTerreIyakiProduct", id);
		try {
			List<ProductComposant> pc01 = (List<ProductComposant>) qr.getResultList();
			return pc01;

		} catch (NullPointerException ex) {
			//
		}
		return null;
	}

	@Override
	public List<ProductComposant> getProductComposantByStatusActifByTerreIyakiProduct(Long id) {
		int numStatus;
		numStatus = 1;

		TypedQuery<ProductComposant> qr = em.createNamedQuery(
				"entity.ProductComposant.getProductComposantByStatusActifByTerreIyakiProduct", ProductComposant.class);
		qr.setParameter("paramStatusNum", numStatus);
		qr.setParameter("paramIdTerreIyakiProduct", id);
		try {
			List<ProductComposant> pc01 = (List<ProductComposant>) qr.getResultList();
			return pc01;

		} catch (NullPointerException ex) {
			//
		}
		return null;
	}

	@Override
	public TerreIyakiProduct getTerreIyakiProductById(Long id) {
		try {
			TerreIyakiProduct te = em.find(TerreIyakiProduct.class, id);
			return te;
		} catch (NullPointerException ex) {
			return null;
		}

	}

	@Override
	public String createFreshProduct(String name, String description) {

		String message = null;
		String picture = "http";

		FreshProduct fe01 = new FreshProduct(name, description, picture);

		List<FreshProduct> li01 = getFreshProductList();

		for (int i = 0; i < li01.size(); i++) {

			if (fe01.getName().equalsIgnoreCase(li01.get(i).getName())) {
				message = "enregistrement impossible : produit existant";
			}

		}
		if (message == null) {

			message = "succès enregistrement";
			em.persist(fe01);
		}

		return message;

	}

	@Override
	public List<TerreIyakiProduct> getTerreIyakiProductListByFreshProduct(String name) {

		TypedQuery<TerreIyakiProduct> qr = em.createNamedQuery(
				"entityTerreIyaki.TerreIyakiProduct.getTerreIyakiProductListByFreshProduct", TerreIyakiProduct.class);
		qr.setParameter("paramName", name);
		try {
			List<TerreIyakiProduct> te01 = (List<TerreIyakiProduct>) qr.getResultList();
			return te01;

		} catch (NullPointerException ex) {
			return null;
		}

	}

	@Override
	public String modifyFreshProduct(String name, String terreIyakiProduct) {
		String message = null;
		FreshProduct pe01 = em.find(FreshProduct.class, name);
		TerreIyakiProduct te01 = getTerreIyakiProductByNamePointeur(terreIyakiProduct);

		Collection<FreshProduct> pe02 = te01.getFreshProducts();

		// System.out.println("test en lazy pe02 : " + pe02.toString());

		for (FreshProduct fe02 : pe02) {
			if (pe01.getName().equalsIgnoreCase(fe02.getName())) {
				message = "impossible d'ajouter le même plat";

			}
		}

		if (message == null) {
			List<FreshProduct> fe01 = new ArrayList();
			fe01.add(pe01);
			te01.setFreshProducts(fe01);

			em.merge(te01);
			//// contraire on va enregistrer produit frais set terreIyaki list
			//
			// List<TerreIyakiProduct> tel01 = new ArrayList();
			// tel01.add(te01);
			// pe01.setTerreIyakiProducts(tel01);
			// em.merge(pe01);
			// message = "enregistrement OK";

		}

		return message;

	}

	private TerreIyakiProduct getTerreIyakiProductByNamePointeur(String name) throws SecurityException {

		TypedQuery<TerreIyakiProduct> qr = em.createNamedQuery(
				"entityTerreIyaki.TerreIyakiProduct.getTerreIyakiProductByNamePointeur", TerreIyakiProduct.class);
		qr.setParameter("paramName", name);

		try {
			TerreIyakiProduct te01 = qr.getSingleResult();
			return te01;

		} catch (NullPointerException ex) {
			return null;
		}

	}

	@Override
	public String createProductComposant(int num, String freshProduct, int amount, User myUser) {

		String message = null;

		// récupère produit frais
		FreshProduct fe01 = em.find(FreshProduct.class, freshProduct);

		// récupère tous les produits composant
		List<ProductComposant> li01 = getProductComposantList();

		// comparaison num + freshProduct doit etre different
		for (int i = 0; i < li01.size(); i++) {
			if (freshProduct.equalsIgnoreCase(li01.get(i).getFreshProduct().getName())) {

				if (num == (li01.get(i).getNum()) && amount == (li01.get(i).getAmount())) {
					message = "Le numéro, le  produit frais et la quantité doivent être différents";

				}

				// else if (num == (li01.get(i).getNum())) {
				// message = "Le produit frais et le numéro doivent être différents";
				//
				// } else if (amount == (li01.get(i).getAmount())) {
				// message = "Le produit frais et la quantité doivent être différents";
				//
				// }

			}

		}
		// si pas égalité cité on persist
		if (message == null) {
			// on recherche status inactif
			Status st01 = em.find(Status.class, 0);
			ProductComposant pc01 = new ProductComposant(num, amount);
			pc01.setFreshProduct(fe01);
			pc01.setStatus(st01);

			em.persist(pc01);

			// +historiser status
			String comment = "Création du composant";
			historiserProductComposantStatus(pc01, st01, myUser, comment);

			message = "enregistrement ok";
		}

		return message;
	}

	private void historiserProductComposantStatus(ProductComposant pc01, Status st01, User myUser, String comment) {

		Date d01 = new Date();
		

		ChangeProductStatus cu01 = new ChangeProductStatus(comment);
		cu01.setProductComposant(pc01);
		cu01.setStatus(st01);

		/*
		 * Historisation contient l'utilisateur qui a effectué le changement
		 */
		Historisation hi01 = new Historisation(d01);
		hi01.setChangeProductStatus(cu01);
		hi01.setUser(myUser);

		em.persist(cu01);
		em.persist(hi01);
	}

	@Override
	public String associerProductComposantWithTerreIyaki(Long id, String terreIyakiProduct) {
		String message = null;

		TerreIyakiProduct te01 = getTerreIyakiProductByNamePointeur(terreIyakiProduct);
		ProductComposant pc01 = em.find(ProductComposant.class, id);

		pc01.setTerreIyakiProduct(te01);
		em.merge(pc01);

		message = "le composant a été associé au plat";

		return message;

	}

	@Override
	public ProductComposant getProductComposantById(Long id) {
		ProductComposant pc01 = em.find(ProductComposant.class, id);
		return pc01;

	}

	@Override
	public List<Status> getStatusList() throws SecurityException {
		List<Status> s01;
		TypedQuery<Status> qr = em.createNamedQuery("entity.Status.getStatusList", Status.class);

		try {
			s01 = (List<Status>) qr.getResultList();
			return s01;

		} catch (NullPointerException ex) {
			return null;
		}

	}

	@Override
	public String activateProductComposant(Long idComposant, String statusComposant, User myUser) {

		String message = null;

		ProductComposant pc01 = em.find(ProductComposant.class, idComposant);

		
		try {
			if (pc01.getStatus().getName().equalsIgnoreCase(statusComposant)) {
			message = "Pas de modification de statut possible pour le même statut";
		
		}

		else {
			Status st01 = getStatusbyName(statusComposant);
			pc01.setStatus(st01);
			em.merge(pc01);
			String comment = "Modification du status";
			historiserProductComposantStatus(pc01, st01, myUser, comment);
			message = "la modification de statut a été enregistré";
		}
		}catch (NullPointerException ex){
			Status st01 = getStatusbyName(statusComposant);
			pc01.setStatus(st01);
			em.merge(pc01);
			String comment = "Création du status";
			historiserProductComposantStatus(pc01, st01, myUser, comment);
			message = "la modification de statut a été enregistré";					
		}

		return message;
	}

	private Status getStatusbyName(String statusComposant) {
		TypedQuery<Status> qr = em.createNamedQuery("entity.Status.getStatusbyName", Status.class);
		qr.setParameter("paramName", statusComposant);
		try {
			Status st01 = qr.getSingleResult();
			return st01;
		} catch (NullPointerException ex) {
			return null;
		}

	}

}
