package entity;

import java.io.Serializable;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: OrderItem
 *
 */
@Entity

public class OrderItem implements Serializable {

	
	private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int amount;
    private float price;
    private float tax;
    
    @ManyToOne
    private FreshProduct freshProduct;
    
    @ManyToOne
    private MyOrder myOrder;
    
    @ManyToOne
    private Location location;
    
	public OrderItem() {
		super();
	}

	public OrderItem(int amount, float price, float tax) {
		this();
		this.amount = amount;
		this.price = price;
		this.tax = tax;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public float getTax() {
		return tax;
	}

	public void setTax(float tax) {
		this.tax = tax;
	}

	public FreshProduct getFreshProduct() {
		return freshProduct;
	}

	public void setFreshProduct(FreshProduct freshProduct) {
		this.freshProduct = freshProduct;
	}

	public MyOrder getMyOrder() {
		return myOrder;
	}

	public void setMyOrder(MyOrder myOrder) {
		this.myOrder = myOrder;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	@Override
	public String toString() {
		return "\nOrderItem [id=" + id + ", amount=" + amount + ", price=" + price + ", tax=" + tax + "]";
	}
	
	
   
}
