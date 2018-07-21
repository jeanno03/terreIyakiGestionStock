package sessionBean.view;

import java.sql.SQLException;
import java.util.List;

import entity.FreshProduct;
import entityTerreIyaki.TerreIyakiProduct;
import entity.ProductComposant;
import entity.Status;
import entity.User;
import tool.CustomException;
import tool.MaPagination;

public interface ProductTreatmentLocal {
	
	public List<TerreIyakiProduct> getTerreIyakiProductList(int debut) throws SecurityException, SQLException;
		
	public List<TerreIyakiProduct> getTerreIyakiProductList() throws SecurityException ;
	
	public List<FreshProduct> getFreshProductList() throws SecurityException;

	public List<ProductComposant> getProductComposantList() throws SecurityException;
	
	public List<ProductComposant> getProductComposantActifList() throws SecurityException;
		
	public List<FreshProduct> getProductComposantListByTerreIyakiProduct(Long id) throws SecurityException;
	
	public List<ProductComposant>  getProductComposantByStatusActifByTerreIyakiProduct(Long id);

	public List<MaPagination> getMaPaginationList();
	
	public TerreIyakiProduct getTerreIyakiProductById(Long id);
	
	public String createFreshProduct(String name, String description);
	
	public List<TerreIyakiProduct> getTerreIyakiProductListByFreshProduct(String name);

	public String modifyFreshProduct(String name, String terreIyakiProduct) ;

	public String createProductComposant(int num, String freshProduct, int amount, User myUser);
		
	public String associerProductComposantWithTerreIyaki(Long id, String terreIyakiProduct);
	
	public ProductComposant getProductComposantById(Long id);
	
	public List<ProductComposant> getProductComposantByTerreIyakiProduct(Long id);

	public List<Status> getStatusList() throws SecurityException;
	
	public String activateProductComposant(Long idComposant, String statusComposant, User myUser );
}
