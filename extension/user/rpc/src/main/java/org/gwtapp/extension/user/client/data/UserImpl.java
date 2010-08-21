package org.gwtapp.extension.user.client.data;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.gwtapp.core.rpc.data.HashModelData;
import org.gwtapp.extension.user.client.data.metafield.EmailMetaField;
import org.gwtapp.extension.user.client.data.metafield.IdMetaField;
import org.gwtapp.extension.user.client.data.metafield.LoginMetaField;
import org.gwtapp.extension.user.client.data.metafield.NameMetaField;

@SuppressWarnings("serial")
@Entity(name = "CCalcUser")
public class UserImpl extends HashModelData implements Serializable {

	public final static IdMetaField ID = new IdMetaField();
	public final static LoginMetaField LOGIN = new LoginMetaField();
	public final static EmailMetaField EMAIL = new EmailMetaField();
	public final static NameMetaField NAME = new NameMetaField();

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
