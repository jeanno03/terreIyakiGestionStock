package sessionBean.view;

import java.util.List;

import entity.ProductComposant;
import entity.User;
import entityTerreIyaki.TerreIyakiOrderItem;
import tool.CustomException;

public interface MyTreatmentLocal {

	public User toConnect(String login, String password) throws SecurityException;
	
	public List<TerreIyakiOrderItem> getTerreIyakiOrderItemList() throws SecurityException ;
		
	public List<ProductComposant> getLastProductComposantSold(int num, String name );
}
