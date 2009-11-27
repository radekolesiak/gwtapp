package org.gwtapp.startapp.client.data;

import java.util.ArrayList;
import java.util.List;

import org.gwtapp.core.client.data.HashModelData;

public class UserRegisterModelImpl extends HashModelData implements
		UserRegisterModel {

	private String login;
	private String email;
	private String password;
	private List<String> tockens = new ArrayList<String>();

	public UserRegisterModelImpl() {
		addAutoField(UserRegisterModel.LOGIN_FIELD);
		addAutoField(UserRegisterModel.EMAIL_FIELD);
		addAutoField(UserRegisterModel.PASSWORD_FIELD);
		addAutoField(UserRegisterModel.TOCKENS_FIELD);
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
	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public void setTockens(List<String> tockens) {
		this.tockens = tockens;
	}

	@Override
	public List<String> getTockens() {
		return tockens;
	}
}
