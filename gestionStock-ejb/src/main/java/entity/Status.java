package entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Status
 *
 */
@Entity
@NamedQueries({
	@NamedQuery(name="entity.Status.getStatusList",
			query = "Select s from Status s"), 
	@NamedQuery(name="entity.Status.getStatusbyName",
	query = "Select s from Status s where s.name =:paramName") 
})
public class Status implements Serializable {

	
	private static final long serialVersionUID = 1L;
	@Id
	private int num;
	private String name;
	
	@OneToMany(mappedBy="status")
	private Collection<ProductComposant> productComposants;
	
	@OneToMany(mappedBy="status")
	private Collection<User> users;
	
	@OneToMany(mappedBy="status")
	private Collection <ChangeProductStatus> changeProductStatus;

	@OneToMany(mappedBy="status")
	private Collection <ChangeUserStatus> changeUserStatus;	
	
	public Status() {
		super();
		productComposants = new ArrayList();
		users = new ArrayList(); 
		changeProductStatus = new ArrayList(); 
		changeUserStatus=new ArrayList(); 
	}
	
	

	public Status(int num, String name) {
		this();
		this.num = num;
		this.name = name;
	}

	public int getNum() {
		return num;
	}



	public void setNum(int num) {
		this.num = num;
	}



	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Collection<ProductComposant> getProductComposants() {
		return productComposants;
	}

	public void setProductComposants(Collection<ProductComposant> productComposants) {
		this.productComposants = productComposants;
	}

	public Collection<User> getUsers() {
		return users;
	}

	public void setUsers(Collection<User> users) {
		this.users = users;
	}

	public Collection<ChangeProductStatus> getStatusChanges() {
		return changeProductStatus;
	}

	public void setStatusChanges(Collection<ChangeProductStatus> changeProductStatus) {
		this.changeProductStatus = changeProductStatus;
	}

	public Collection<ChangeProductStatus> getStatusChangeProducts() {
		return changeProductStatus;
	}



	public void setStatusChangeProducts(Collection<ChangeProductStatus> changeProductStatus) {
		this.changeProductStatus = changeProductStatus;
	}



	public Collection<ChangeUserStatus> getStatusChangeUser() {
		return changeUserStatus;
	}



	public void setStatusChangeUser(Collection<ChangeUserStatus> changeUserStatus) {
		this.changeUserStatus = changeUserStatus;
	}



	public Collection<ChangeProductStatus> getChangeProductStatus() {
		return changeProductStatus;
	}



	public void setChangeProductStatus(Collection<ChangeProductStatus> changeProductStatus) {
		this.changeProductStatus = changeProductStatus;
	}



	public Collection<ChangeUserStatus> getChangeUserStatus() {
		return changeUserStatus;
	}



	public void setChangeUserStatus(Collection<ChangeUserStatus> changeUserStatus) {
		this.changeUserStatus = changeUserStatus;
	}



	@Override
	public String toString() {
		return "\nStatus [num=" + num + ", name=" + name + "]";
	}
	
	
	
	
   
}
