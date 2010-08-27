package org.gwtapp.extension.user.client.data;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.gwtapp.core.rpc.data.HashModelData;

import com.google.inject.Inject;

@SuppressWarnings("serial")
@Entity(name = "UserPasswordEntity")
public class UserPasswordImpl extends HashModelData implements UserPassword {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id = ID.add(this).def();

	@Basic
	@Column(nullable = false)
	private String password = PASSWORD.add(this).def();

	@Inject
	@Basic
	@Column(nullable = false)
	private User user = USER.add(this).def();

	public UserPasswordImpl() {
	}

	public UserPasswordImpl(String login, String email, String name) {
		setUser(new UserImpl(login, email, name));
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
	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public User getUser() {
		return user;
	}

	@Override
	public void setUser(User user) {
		this.user = user;
	}
}
