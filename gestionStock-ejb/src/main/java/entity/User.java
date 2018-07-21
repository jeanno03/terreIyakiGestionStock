package entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: User
 *
 */
@Entity
@Table(name="UTILISATEUR")
@NamedQueries({
	@NamedQuery(name="entity.User.getUserList",
			query = "Select u from User u"),
	@NamedQuery(name="entity.User.getUserActifList",
	query = "Select u from User u where u.status.num =:paramNumActif") 
})
public class User implements Serializable {


	private static final long serialVersionUID = 1L;
	@Id
	private String login;
	private String password;
	private String email;
	private String firstName;
	private String lastName;

	@ManyToOne
	private GrantUser grantUser;
	
	@ManyToOne
	private Status status;
	
	@OneToMany(mappedBy="user")
	private Collection <MyOrder> myOrders;	
	
	@OneToMany(mappedBy="user")
	private Collection <ChangeUserStatus> changeUserStatus;
	
	@OneToMany(mappedBy="user") 
	private Collection  <ChangeUserGrantUser> changeUserGrantUser;
	
	@OneToMany(mappedBy="user")
	private Collection<Historisation> historisations;
	
	@OneToMany(mappedBy="user")
	private  Collection<Trash> trashes;
	

	public User() {
		super();
		myOrders = new ArrayList();
		changeUserStatus= new ArrayList();
		changeUserGrantUser= new ArrayList();
		historisations= new ArrayList();
		trashes = new ArrayList();
	}

	public User(String login, String password, String email, String firstName, String lastName) {
		this();
		this.login = login;
		this.password = password;
		this.email = email;
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public GrantUser getGrantUser() {
		return grantUser;
	}

	public void setGrantUser(GrantUser grantUser) {
		this.grantUser = grantUser;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public Collection<MyOrder> getMyOrders() {
		return myOrders;
	}

	public void setMyOrders(Collection<MyOrder> myOrders) {
		this.myOrders = myOrders;
	}

	public Collection<ChangeUserStatus> getChangeUserStatus() {
		return changeUserStatus;
	}

	public void setChangeUserStatus(Collection<ChangeUserStatus> changeUserStatus) {
		this.changeUserStatus = changeUserStatus;
	}

	public Collection<ChangeUserGrantUser> getChangeUserGrantUser() {
		return changeUserGrantUser;
	}

	public void setChangeUserGrantUser(Collection<ChangeUserGrantUser> changeUserGrantUser) {
		this.changeUserGrantUser = changeUserGrantUser;
	}

	public Collection<Historisation> getHistorisations() {
		return historisations;
	}

	public void setHistorisations(Collection<Historisation> historisations) {
		this.historisations = historisations;
	}

	public Collection<Trash> getTrashes() {
		return trashes;
	}

	public void setTrashes(Collection<Trash> trashes) {
		this.trashes = trashes;
	}

	@Override
	public String toString() {
		return "\nUser [login=" + login + ", password=" + password + ", email=" + email + ", firstName=" + firstName
				+ ", lastName=" + lastName + "]";
	}

	

}







