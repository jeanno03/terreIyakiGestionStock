package entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: GrantUser
 *
 */
@Entity
@NamedQueries({
	@NamedQuery(name="entity.GrantUser.getGrantUserList",
			query = "Select g from GrantUser g") 
})
public class GrantUser implements Serializable {

	
	private static final long serialVersionUID = 1L;
	@Id
	private String name;
	
@OneToMany(mappedBy="grantUser")	
private Collection<User> users;

@OneToMany(mappedBy="grantUser")
private Collection <ChangeUserGrantUser> changeUserGrantUsers;

public GrantUser() {
	super();
	users= new ArrayList();
}

public GrantUser(String name) {
	this();
	this.name = name;
}

public String getName() {
	return name;
}

public void setName(String name) {
	this.name = name;
}

public Collection<User> getUsers() {
	return users;
}

public void setUsers(Collection<User> users) {
	this.users = users;
}

@Override
public String toString() {
	return "\nGrantUser [name=" + name + ", users=" + users + "]";
}




	
   
}
