package biz.cuprum.gwtapp.client.data;

import biz.cuprum.gwtapp.core.client.data.ModelData;

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

	@SuppressWarnings("unchecked")
	@Override
	public <X> X get(String property) {
		if (LOGIN.equals(property)) {
			return (X) login;
		} else if (EMAIL.equals(property)) {
			return (X) email;
		} else if (PASSWORD.equals(property)) {
			return (X) password;
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public <X> X set(String property, X value) {
		X old = null;
		if (LOGIN.equals(property)) {
			old = (X) login;
			login = (String) value;
		} else if (EMAIL.equals(property)) {
			old = (X) email;
			email = (String) value;
		} else if (PASSWORD.equals(property)) {
			old = (X) password;
			password = (String) value;
		}
		return old;
	}
}
