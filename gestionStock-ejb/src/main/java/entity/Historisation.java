package entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Historisation
 *
 */
@Entity
@NamedQueries({
	@NamedQuery(name="entity.getHistorisationByUser",
			query = "Select h from Historisation h where h.user.login =:paramId")
})
public class Historisation implements Serializable {

	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
private Date historisationDate ;

@ManyToOne
private User user;

@OneToOne
private ChangeUserStatus changeUserStatus;

@OneToOne
private ChangeProductStatus changeProductStatus;

@OneToOne
private ChangeUserGrantUser changeUserGrantUser;

@OneToOne
private Trash trash;


	public Historisation() {
		super();
	}

	public Historisation(Date historisationDate) {
		this();
		this.historisationDate = historisationDate;
	}
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Date getHistorisationDate() {
		return historisationDate;
	}

	public void setHistorisationDate(Date historisationDate) {
		this.historisationDate = historisationDate;
	}

	public ChangeUserStatus getChangeUserStatus() {
		return changeUserStatus;
	}

	public void setChangeUserStatus(ChangeUserStatus changeUserStatus) {
		this.changeUserStatus = changeUserStatus;
	}

	public ChangeProductStatus getChangeProductStatus() {
		return changeProductStatus;
	}

	public void setChangeProductStatus(ChangeProductStatus changeProductStatus) {
		this.changeProductStatus = changeProductStatus;
	}

	public ChangeUserGrantUser getChangeUserGrantUser() {
		return changeUserGrantUser;
	}

	public void setChangeUserGrantUser(ChangeUserGrantUser changeUserGrantUser) {
		this.changeUserGrantUser = changeUserGrantUser;
	}

	public Trash getTrash() {
		return trash;
	}

	public void setTrash(Trash trash) {
		this.trash = trash;
	}

	@Override
	public String toString() {
		return "\nHistorisation [id=" + id + ", historisationDate=" + historisationDate + "]";
	}
   
}
