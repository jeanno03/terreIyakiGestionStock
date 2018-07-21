package entityTerreIyaki;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: TerreIyakiOrderItem
 *
 */
@Entity
@NamedQueries({
	@NamedQuery(name="entityTerreIyaki.TerreIyakiOrderItem.getTerreIyakiOrderItemList",
			query = "Select t from TerreIyakiOrderItem t order by t.orderItem_idPointeur desc")
})
public class TerreIyakiOrderItem implements Serializable {


	private static final long serialVersionUID = 1L;

	@Id
	private Long orderItem_idPointeur;
	private float pricePointeur;
	private float taxPointeur;
	private Long terreIyakiOrder_idPointeur;


	@ManyToOne
	private TerreIyakiProduct terreIyakiProduct;

	public TerreIyakiOrderItem() {
		super();
	}

	public TerreIyakiOrderItem(Long orderItem_idPointeur, float pricePointeur, float taxPointeur,
			Long terreIyakiOrder_idPointeur, TerreIyakiProduct terreIyakiProduct) {
		this();
		this.orderItem_idPointeur = orderItem_idPointeur;
		this.pricePointeur = pricePointeur;
		this.taxPointeur = taxPointeur;
		this.terreIyakiOrder_idPointeur = terreIyakiOrder_idPointeur;
		this.terreIyakiProduct = terreIyakiProduct;
	}

	public Long getOrderItem_idPointeur() {
		return orderItem_idPointeur;
	}

	public void setOrderItem_idPointeur(Long orderItem_idPointeur) {
		this.orderItem_idPointeur = orderItem_idPointeur;
	}

	public float getPricePointeur() {
		return pricePointeur;
	}

	public void setPricePointeur(float pricePointeur) {
		this.pricePointeur = pricePointeur;
	}

	public float getTaxPointeur() {
		return taxPointeur;
	}

	public void setTaxPointeur(float taxPointeur) {
		this.taxPointeur = taxPointeur;
	}

	public Long getTerreIyakiOrder_idPointeur() {
		return terreIyakiOrder_idPointeur;
	}

	public void setTerreIyakiOrder_idPointeur(Long terreIyakiOrder_idPointeur) {
		this.terreIyakiOrder_idPointeur = terreIyakiOrder_idPointeur;
	}

	public TerreIyakiProduct getTerreIyakiProduct() {
		return terreIyakiProduct;
	}

	public void setTerreIyakiProduct(TerreIyakiProduct terreIyakiProduct) {
		this.terreIyakiProduct = terreIyakiProduct;
	}
	
//	public float getPriceTTC() {
//		float priceTTC=0f;
//		float pricePointeur = this.pricePointeur;
//		float taxPointeur = this.taxPointeur;
//		priceTTC= pricePointeur - (pricePointeur*taxPointeur/100);
//		return priceTTC;
//	}

	@Override
	public String toString() {
		return "\nTerreIyakiOrderItem [orderItem_idPointeur=" + orderItem_idPointeur + ", pricePointeur=" + pricePointeur
				+ ", taxPointeur=" + taxPointeur + ", terreIyakiOrder_idPointeur=" + terreIyakiOrder_idPointeur
				+ ", terreIyakiProduct=" + terreIyakiProduct + "]";
	}





}
