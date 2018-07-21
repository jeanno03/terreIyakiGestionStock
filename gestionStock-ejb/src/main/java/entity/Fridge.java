package entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Fridge
 *
 */
@Entity

public class Fridge implements Serializable {

	
	private static final long serialVersionUID = 1L;

	@Id
	private String name;
	private int capacity;
	
	@OneToMany(mappedBy="fridge")
	private Collection <Location> locations;
	
	public Fridge() {
		super();
		locations = new ArrayList();
	}


	public Fridge(String name, int capacity) {
		this();
		this.name = name;
		this.capacity = capacity;
	}


	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	public Collection<Location> getLocations() {
		return locations;
	}

	public void setLocations(Collection<Location> locations) {
		this.locations = locations;
	}

	@Override
	public String toString() {
		return "\nFridge [name=" + name + ", capacity=" + capacity + "]";
	}
	
	
   
}
