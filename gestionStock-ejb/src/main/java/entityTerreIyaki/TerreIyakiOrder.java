package entityTerreIyaki;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: TerreIyakiOrder
 *
 */
@Entity
public class TerreIyakiOrder implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	private Long myorder_idPointeur;
	private Date dailyDatePointeur;

	public TerreIyakiOrder() {
		super();
	}

	public TerreIyakiOrder(Long myorder_idPointeur, Date dailyDatePointeur) {
		super();
		this.myorder_idPointeur = myorder_idPointeur;
		this.dailyDatePointeur = dailyDatePointeur;
	}

	public Long getMyorder_idPointeur() {
		return myorder_idPointeur;
	}

	public void setMyorder_idPointeur(Long myorder_idPointeur) {
		this.myorder_idPointeur = myorder_idPointeur;
	}

	public Date getDailyDatePointeur() {
		return dailyDatePointeur;
	}

	public void setDailyDatePointeur(Date dailyDatePointeur) {
		this.dailyDatePointeur = dailyDatePointeur;
	}

	@Override
	public String toString() {
		return "\nTerreIyakiOrder [myorder_idPointeur=" + myorder_idPointeur + ", dailyDatePointeur="
				+ dailyDatePointeur + "]";
	}

}
