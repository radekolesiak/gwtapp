package org.gwtapp.extension.user.client.data;

import org.gwtapp.extension.user.client.data.metafield.PasswordMetaField;
import org.gwtapp.extension.user.client.data.metafield.PasswordVerifyMetaField;

@SuppressWarnings("serial")
public class UserPassword extends User {

	public final static PasswordMetaField PASSWORD = new PasswordMetaField();
	public final static PasswordVerifyMetaField PASSWORD_VERIFY = new PasswordVerifyMetaField();

	public UserPassword() {
	}

	public UserPassword(String login, String email, String name) {
		super(login, email, name);
	}

	private String password = PASSWORD.add(this).def();
	private String passwordVerify = PASSWORD_VERIFY.add(this).def();

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPassword() {
		return password;
	}

	public void setPasswordVerify(String passwordVerify) {
		this.passwordVerify = passwordVerify;
	}

	public String getPasswordVerify() {
		return passwordVerify;
	}
}
