package org.gwtapp.startapp.client.data.user.register;

import java.util.List;

import org.gwtapp.core.rpc.data.HashModelData;

public class UserRegisterModelImpl extends HashModelData implements
		UserRegisterModel {

	private String login = UserRegister.LOGIN.add(this).def();
	private String email = UserRegister.EMAIL.add(this).def();
	private String password = UserRegister.PASSWORD.add(this).def();
	private List<String> tockens = UserRegister.TOCKENS.add(this).def();

	public UserRegisterModelImpl() {
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
