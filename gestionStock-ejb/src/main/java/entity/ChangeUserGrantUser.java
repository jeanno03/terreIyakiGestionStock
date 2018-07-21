package entity;

import java.io.Serializable;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: ChangeUserGrantUser
 *
 */
@Entity

public class ChangeUserGrantUser implements Serializable {

	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String comment;
	
	@ManyToOne
	private GrantUser grantUser;

	@ManyToOne
	private User user;
	
	@OneToOne(mappedBy="changeUserGrantUser")
	private Historisation historisation;
	
	public ChangeUserGrantUser() {
		super();
	}

	public ChangeUserGrantUser(String comment) {
		this();
		this.comment = comment;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public GrantUser getGrantUser() {
		return grantUser;
	}

	public void setGrantUser(GrantUser grantUser) {
		this.grantUser = grantUser;
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

	@Override
	public String toString() {
		return "\nChangeUserGrantUser [id=" + id + ", comment=" + comment + "]";
	}
	
	
	
   
}
