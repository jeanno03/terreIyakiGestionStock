package entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.*;

import entityTerreIyaki.TerreIyakiProduct;

/**
 * Entity implementation class for Entity: FreshProduct
 *
 */
@Entity
@NamedQueries({
	@NamedQuery(name="entity.FreshProduct.getFreshProductList",
			query = "Select f from FreshProduct f"),
    @NamedQuery(name = "entity.FreshProduct.getFreshProductListByTerreIyakiProduct",
    query = "select f from FreshProduct f join f.terreIyakiProducts p where p.product_idPointeur = :paramId")
})
public class FreshProduct implements Serializable {

	
	private static final long serialVersionUID = 1L;
	
	@Id
	private String name;
	private String description;
	private String picture;
	
    @ManyToMany(mappedBy="freshProducts")
    private Collection<TerreIyakiProduct> terreIyakiProducts;	
    
    @OneToMany(mappedBy="freshProduct")
    private Collection<ProductComposant> productComposants;
    
    @OneToMany(mappedBy="freshProduct")
    private Collection<OrderItem> orderItems;
    
    @OneToOne(mappedBy="freshProduct")
    private Location location;   
    
	@OneToMany(mappedBy="freshProduct")
	private  Collection<Trash> trashes;

	public FreshProduct() {
		super();
		terreIyakiProducts = new ArrayList();
		productComposants = new ArrayList();
		orderItems = new ArrayList();
		trashes = new ArrayList();
	}

	public FreshProduct(String name, String description, String picture) {
		this();
		this.name = name;
		this.description = description;
		this.picture = picture;
	}
	
	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public Collection<TerreIyakiProduct> getTerreIyakiProducts() {
		return terreIyakiProducts;
	}

	public void setTerreIyakiProducts(Collection<TerreIyakiProduct> terreIyakiProducts) {
		this.terreIyakiProducts = terreIyakiProducts;
	}

	public Collection<ProductComposant> getProductComposants() {
		return productComposants;
	}

	public void setProductComposants(Collection<ProductComposant> productComposants) {
		this.productComposants = productComposants;
	}

	public Collection<OrderItem> getOrderItems() {
		return orderItems;
	}

	public void setOrderItems(Collection<OrderItem> orderItems) {
		this.orderItems = orderItems;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public Collection<Trash> getTrashes() {
		return trashes;
	}

	public void setTrashes(Collection<Trash> trashes) {
		this.trashes = trashes;
	}

	@Override
	public String toString() {
		return "\nFreshProduct [name=" + name + ", description=" + description + ", picture=" + picture + "]";
	}
   
	
	
}
