package entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.*;

import entityTerreIyaki.TerreIyakiProduct;

/**
 * Entity implementation class for Entity: ProductComposant
 *
 */
@Entity
@NamedQueries({
	@NamedQuery(name="entity.ProductComposant.getProductComposantList",
			query = "Select p from ProductComposant p"),
	@NamedQuery(name="entity.ProductComposant.getProductComposantByStatusActifByTerreIyakiProduct",
	query = "Select p from ProductComposant p where p.status.num = :paramStatusNum and p.terreIyakiProduct.product_idPointeur = :paramIdTerreIyakiProduct"),
	@NamedQuery(name="entity.ProductComposant.getProductComposantActifList",
	query = "Select p from ProductComposant p where p.status.num = :paramId"),
	@NamedQuery(name="entity.ProductComposant.getProductComposantByTerreIyakiProduct",
	query = "Select p from ProductComposant p where p.terreIyakiProduct.product_idPointeur = :paramIdTerreIyakiProduct"),
	@NamedQuery(name="entity.ProductComposant.getProductComposantListByTerreIyakiOrderItem",
	query = "Select p from ProductComposant p join p.terreIyakiProduct t where p.status.num = :paramNumStatus and t.namePointeur = :paramName")
})
public class ProductComposant implements Serializable {


	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private int num;
	private int amount;

	@ManyToOne
	private TerreIyakiProduct terreIyakiProduct;

	@ManyToOne
	private FreshProduct freshProduct;

	@ManyToOne
	private Status status;
	
	@OneToMany(mappedBy="productComposant")
	private Collection <ChangeProductStatus> changeProductStatus;



	public ProductComposant() {
		super();
		changeProductStatus= new ArrayList();
	}









	public ProductComposant(int num, int amount) {
		super();
		this.num = num;
		this.amount = amount;
	}









	public ProductComposant(Long id, int num, int amount) {
		super();
		this.id = id;
		this.num = num;
		this.amount = amount;
	}









	public Long getId() {
		return id;
	}









	public void setId(Long id) {
		this.id = id;
	}









	public int getNum() {
		return num;
	}




	public void setNum(int num) {
		this.num = num;
	}




	public int getAmount() {
		return amount;
	}




	public void setAmount(int amount) {
		this.amount = amount;
	}




	public TerreIyakiProduct getTerreIyakiProduct() {
		return terreIyakiProduct;
	}




	public void setTerreIyakiProduct(TerreIyakiProduct terreIyakiProduct) {
		this.terreIyakiProduct = terreIyakiProduct;
	}






	public FreshProduct getFreshProduct() {
		return freshProduct;
	}




	public void setFreshProduct(FreshProduct freshProduct) {
		this.freshProduct = freshProduct;
	}




	public Status getStatus() {
		return status;
	}




	public void setStatus(Status status) {
		this.status = status;
	}


	public Collection<ChangeProductStatus> getStatusChanges() {
		return changeProductStatus;
	}




	public void setStatusChanges(Collection<ChangeProductStatus> changeProductStatus) {
		this.changeProductStatus = changeProductStatus;
	}




	public Collection<ChangeProductStatus> getChangeProductStatus() {
		return changeProductStatus;
	}




	public void setChangeProductStatus(Collection<ChangeProductStatus> changeProductStatus) {
		this.changeProductStatus = changeProductStatus;
	}




	@Override
	public String toString() {
		return "\nProductComposant [num=" + num + ", amount=" + amount + "]";
	}




}
