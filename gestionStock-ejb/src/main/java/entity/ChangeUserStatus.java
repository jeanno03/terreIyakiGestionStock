package entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: StatusChangeUser
 *
 */

@Entity
@NamedQueries({
	@NamedQuery(name="entity.getChangeUserStatusByUser",
			query = "Select c from ChangeUserStatus c where c.user.login =:paramId")
})
public class ChangeUserStatus implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String comment;

	@ManyToOne
	private User user;

	@OneToOne(mappedBy = "changeUserStatus")
	private Historisation historisation;

	@ManyToOne
	private Status status;

	public ChangeUserStatus() {
		super();
	}

	public ChangeUserStatus(String comment) {
		this();
		this.comment = comment;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Historisation getHistorisation() {
		return historisation;
	}

	public void setHistorisation(Historisation historisation) {
		this.historisation = historisation;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "\nChangeUserStatus [id=" + id + ", comment=" + comment + "]";
	}




}
