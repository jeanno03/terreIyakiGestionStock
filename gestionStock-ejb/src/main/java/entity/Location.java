package entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Location
 *
 */
@Entity

public class Location implements Serializable {

	
	private static final long serialVersionUID = 1L;
    @Id
    private int num;
    
    private int amount;
    
    @ManyToOne
    private Fridge fridge;
    
    @OneToOne
    private FreshProduct freshProduct;
    
    @OneToMany(mappedBy="location")
    private Collection<OrderItem> orderItems;

	public Location() {
		super();
		orderItems = new ArrayList();
	}



	public Location(int num, int amount) {
		this();
		this.num = num;
		this.amount = amount;
	}



	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public Fridge getFridge() {
		return fridge;
	}

	public void setFridge(Fridge fridge) {
		this.fridge = fridge;
	}


	public FreshProduct getFreshProduct() {
		return freshProduct;
	}



	public void setFreshProduct(FreshProduct freshProduct) {
		this.freshProduct = freshProduct;
	}



	public Collection<OrderItem> getOrderItems() {
		return orderItems;
	}

	public void setOrderItems(Collection<OrderItem> orderItems) {
		this.orderItems = orderItems;
	}



	@Override
	public String toString() {
		return "\nLocation [num=" + num + ", amount=" + amount + "]";
	}


	
	
	
   
}
