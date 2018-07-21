package entityTerreIyaki;

import java.io.Serializable;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Mail
 *
 */
@Entity
@NamedQueries({
	@NamedQuery(name="entityTerreIyaki.Mail.getIdentifiant",
			query = "Select m from Mail m") 
})
public class Mail implements Serializable {

	
	private static final long serialVersionUID = 1L;
	@Id
	private String mail;
	   private String mdp;

	public Mail() {
		super();
	}
	
	

	public Mail(String mail, String mdp) {
		super();
		this.mail = mail;
		this.mdp = mdp;
	}



	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getMdp() {
		return mdp;
	}

	public void setMdp(String mdp) {
		this.mdp = mdp;
	}
   
}
