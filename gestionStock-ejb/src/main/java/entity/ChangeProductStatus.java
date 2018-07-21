package entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: StatusChangeProduct
 *
 */
@Entity
public class ChangeProductStatus implements Serializable {

	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String comment;

	@ManyToOne
	private ProductComposant productComposant;

	@ManyToOne
	private Status status;
	
	@OneToOne(mappedBy="changeProductStatus")
	private Historisation historisation;

	public ChangeProductStatus() {
		super();
	}

	public ChangeProductStatus(String comment) {
		this();
		this.comment = comment;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public ProductComposant getProductComposant() {
		return productComposant;
	}

	public void setProductComposant(ProductComposant productComposant) {
		this.productComposant = productComposant;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public Historisation getHistorisation() {
		return historisation;
	}

	public void setHistorisation(Historisation historisation) {
		this.historisation = historisation;
	}

	@Override
	public String toString() {
		return "\nChangeProductStatus [id=" + id + ", comment=" + comment + "]";
	}


}
