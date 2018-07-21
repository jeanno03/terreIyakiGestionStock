package tool;

import java.io.Serializable;

/*
 * On fixe à 5 le nombre de produits à afficher par page
 * return la valeur de début et de fin de chaque pagination
 */
public class MaPagination implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4667045746374426454L;
	
	private int debut;
	private int fin;
	
	public MaPagination() {
		super();
	}

	


	public MaPagination(int debut) {
		super();
		this.debut = debut;
	}




	public MaPagination(int debut, int fin) {
		super();
		this.debut = debut;
		this.fin = fin;
	}



	public int getDebut() {
		return debut;
	}

	public void setDebut(int debut) {
		this.debut = debut;
	}
	
	

	public int getFin() {
		return fin;
	}



	public void setFin(int fin) {
		this.fin = fin;
	}



	public int getFauxFin() {
		int fin = this.debut + 4;
		return fin;
	}

	@Override
	public String toString() {
		return "\nPagination [debut=" + debut + ", getFauxFin=" + getFauxFin() + "]";
	}

}
