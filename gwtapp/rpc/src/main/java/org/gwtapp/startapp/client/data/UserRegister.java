package org.gwtapp.startapp.client.data;

import org.gwtapp.core.client.data.ModelData;

import com.google.gwt.user.client.rpc.IsSerializable;

public class UserRegister implements ModelData, IsSerializable {

	public final static String LOGIN = "login";
	public final static String EMAIL = "email";
	public final static String PASSWORD = "password";

	private String login;
	private String password;
	private String email;

	public void setLogin(String login) {
		this.login = login;
	}

	public String getLogin() {
		return login;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPassword() {
		return password;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEmail() {
		return email;
	}

	@Override
	public String toString() {
		return login + " " + password + " " + email;
	}

	@Override
	public Object get(String property) {
		if (LOGIN.equals(property)) {
			return login;
		} else if (EMAIL.equals(property)) {
			return email;
		} else if (PASSWORD.equals(property)) {
			return password;
		}
		return null;
	}

	@Override
	public Object set(String property, Object value) {
		Object old = null;
		if (LOGIN.equals(property)) {
			old = login;
			login = (String) value;
		} else if (EMAIL.equals(property)) {
			old = email;
			email = (String) value;
		} else if (PASSWORD.equals(property)) {
			old = password;
			password = (String) value;
		}
		return old;
	}
}
