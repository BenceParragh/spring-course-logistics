package hu.cubix.logistics.bencepar.model;

import java.util.Set;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;

@Entity
public class LogisticsUser {

	@Id
	private String username;
	private String password;

	@ElementCollection(fetch = FetchType.EAGER)
	private Set<String> roles;

	public LogisticsUser() {
		super();
	}

	public LogisticsUser(String username, String password, Set<String> roles) {
		super();
		this.username = username;
		this.password = password;
		this.roles = roles;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Set<String> getRoles() {
		return roles;
	}

	public void setRoles(Set<String> roles) {
		this.roles = roles;
	}

}
