package org.gwtapp.extension.user.client.data;

import org.gwtapp.extension.user.client.data.metafield.PasswordMetaField;

@SuppressWarnings("serial")
public class UserPassword extends User {

	public final static PasswordMetaField PASSWORD = new PasswordMetaField();

	public UserPassword() {
	}

	public UserPassword(String login, String email, String name) {
		super(login, email, name);
	}

	private String password = PASSWORD.add(this).def();

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPassword() {
		return password;
	}
}
