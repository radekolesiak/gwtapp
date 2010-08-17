package org.gwtapp.extension.user.client.data;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "CCalcUser")
public class User implements Serializable {

	private static final long serialVersionUID = 2672857431181845032L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Basic
	@Column(nullable = false, unique = true)
	private String login;

	@Basic
	@Column(nullable = false, unique = true)
	private String email;

	@Basic
	private String name;

	public User() {
	}

	public User(String login, String email, String name) {
		setLogin(login);
		setEmail(email);
		setName(name);
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getLogin() {
		return login;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEmail() {
		return email;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
}
