package org.gwtapp.extension.user.client.data;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.gwtapp.core.rpc.data.HashModelData;

@SuppressWarnings("serial")
@Entity(name = "CCalcUser")
public class UserImpl extends HashModelData implements User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id = ID.add(this).def();

	@Basic
	@Column(nullable = false, unique = true)
	private String login = LOGIN.add(this).def();

	@Basic
	@Column(nullable = false, unique = true)
	private String email = EMAIL.add(this).def();

	@Basic
	private String name = NAME.add(this).def();

	public UserImpl() {
	}

	public UserImpl(String login, String email, String name) {
		setLogin(login);
		setEmail(email);
		setName(name);
	}

	@Override
	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public Long getId() {
		return id;
	}

	@Override
	public void setLogin(String login) {
		this.login = login;
	}

	@Override
	public String getLogin() {
		return login;
	}

	@Override
	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String getEmail() {
		return email;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String getName() {
		return name;
	}
}
