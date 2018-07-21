package entity;

import java.io.Serializable;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Trash
 *
 */
@Entity

public class Trash implements Serializable {

	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private int amount;
	
	@ManyToOne
	private User user;
	
	@OneToOne(mappedBy="trash")
	private Historisation historisation;
	
    @ManyToOne
    private FreshProduct freshProduct;
	

	public Trash() {
		super();
	}

	public Trash(int amount) {
		this();
		this.amount = amount;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
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

	public FreshProduct getFreshProduct() {
		return freshProduct;
	}

	public void setFreshProduct(FreshProduct freshProduct) {
		this.freshProduct = freshProduct;
	}

	@Override
	public String toString() {
		return "\nTrash [id=" + id + ", amount=" + amount + "]";
	}
	
	
   
}
