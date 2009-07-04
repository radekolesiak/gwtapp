package biz.cuprum.gwtapp.client.data;

import com.google.gwt.user.client.rpc.IsSerializable;

public class UserRegister implements IsSerializable {

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
}
