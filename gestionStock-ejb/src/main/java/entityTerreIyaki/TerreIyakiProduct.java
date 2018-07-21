package entityTerreIyaki;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.*;

import entity.FreshProduct;
import entity.ProductComposant;

/**
 * Entity implementation class for Entity: TerreIyakiProduct
 *
 */
@Entity
@NamedQueries({
	@NamedQuery(name="entityTerreIyaki.TerreIyakiProduct.getTerreIyakiProductList",
			query = "Select t from TerreIyakiProduct t"),
    @NamedQuery(name = "entityTerreIyaki.TerreIyakiProduct.getTerreIyakiProductListByFreshProduct",
    query = "select t from TerreIyakiProduct t join t.freshProducts f where f.name = :paramName"),
	@NamedQuery(name="entityTerreIyaki.TerreIyakiProduct.getTerreIyakiProductByNamePointeur",
	query = "Select t from TerreIyakiProduct t where t.namePointeur = :paramName")
})
public class TerreIyakiProduct implements Serializable {


	private static final long serialVersionUID = 1L;
	
	@Id
	private Long product_idPointeur;
	private String namePointeur;
	private float pricePointeur;
	private String picturePointeur;
	private String descriptionPointeur;

	@OneToMany(mappedBy="terreIyakiProduct")
	private Collection <TerreIyakiOrderItem> terreIyakiOrderItems;

	@OneToMany(mappedBy="terreIyakiProduct")
	private Collection<ProductComposant> productComposants;
	
	@ManyToMany
	private Collection<FreshProduct> freshProducts;

	public TerreIyakiProduct() {
		super();
		terreIyakiOrderItems = new ArrayList();
		productComposants = new ArrayList();
		freshProducts = new ArrayList();
	}

	public TerreIyakiProduct(Long product_idPointeur, String namePointeur, float pricePointeur, String picturePointeur,
			String descriptionPointeur) {
		super();
		this.product_idPointeur = product_idPointeur;
		this.namePointeur = namePointeur;
		this.pricePointeur = pricePointeur;
		this.picturePointeur = picturePointeur;
		this.descriptionPointeur = descriptionPointeur;
	}

	
	public Long getProduct_idPointeur() {
		return product_idPointeur;
	}

	public void setProduct_idPointeur(Long product_idPointeur) {
		this.product_idPointeur = product_idPointeur;
	}

	public String getNamePointeur() {
		return namePointeur;
	}

	public void setNamePointeur(String namePointeur) {
		this.namePointeur = namePointeur;
	}

	public float getPricePointeur() {
		return pricePointeur;
	}

	public void setPricePointeur(float pricePointeur) {
		this.pricePointeur = pricePointeur;
	}

	public String getPicturePointeur() {
		return picturePointeur;
	}

	public void setPicturePointeur(String picturePointeur) {
		this.picturePointeur = picturePointeur;
	}

	public String getDescriptionPointeur() {
		return descriptionPointeur;
	}

	public void setDescriptionPointeur(String descriptionPointeur) {
		this.descriptionPointeur = descriptionPointeur;
	}

	public Collection<TerreIyakiOrderItem> getTerreIyakiOrderItems() {
		return terreIyakiOrderItems;
	}

	public void setTerreIyakiOrderItems(Collection<TerreIyakiOrderItem> terreIyakiOrderItems) {
		this.terreIyakiOrderItems = terreIyakiOrderItems;
	}

	public Collection<ProductComposant> getProductComposants() {
		return productComposants;
	}

	public void setProductComposants(Collection<ProductComposant> productComposants) {
		this.productComposants = productComposants;
	}

	public Collection<FreshProduct> getFreshProducts() {
		return freshProducts;
	}

	public void setFreshProducts(Collection<FreshProduct> freshProducts) {
		this.freshProducts = freshProducts;
	}

	@Override
	public String toString() {
		return "\nTerreIyakiProduct [product_idPointeur=" + product_idPointeur + ", namePointeur=" + namePointeur
				+ ", pricePointeur=" + pricePointeur + ", picturePointeur=" + picturePointeur + ", descriptionPointeur="
				+ descriptionPointeur + "]";
	}

	

}
