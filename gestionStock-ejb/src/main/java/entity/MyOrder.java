package entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: MyOrder
 *
 */
@Entity

public class MyOrder implements Serializable {

	
	private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date registeredDate;
    
    @ManyToOne
    private User user;
    
    @OneToMany(mappedBy="myOrder")
    private Collection<OrderItem> orderItems;
    

	public MyOrder() {
		super();
		orderItems = new ArrayList();
	}


	public MyOrder(Date registeredDate) {
		this();
		this.registeredDate = registeredDate;
	}


	public Date getRegisteredDate() {
		return registeredDate;
	}


	public void setRegisteredDate(Date registeredDate) {
		this.registeredDate = registeredDate;
	}


	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}


	public Collection<OrderItem> getOrderItems() {
		return orderItems;
	}


	public void setOrderItems(Collection<OrderItem> orderItems) {
		this.orderItems = orderItems;
	}


	@Override
	public String toString() {
		return "\nMyOrder [registeredDate=" + registeredDate + "]";
	}
	
	
   
}
