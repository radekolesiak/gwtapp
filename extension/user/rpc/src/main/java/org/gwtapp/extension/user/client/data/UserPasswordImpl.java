package org.gwtapp.extension.user.client.data;

@SuppressWarnings("serial")
public class UserPasswordImpl extends UserImpl implements UserPassword {

	private String password = PASSWORD.add(this).def();
	private String passwordVerify = PASSWORD_VERIFY.add(this).def();

	public UserPasswordImpl() {
	}

	public UserPasswordImpl(String login, String email, String name) {
		super(login, email, name);
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
	public void setPasswordVerify(String passwordVerify) {
		this.passwordVerify = passwordVerify;
	}

	@Override
	public String getPasswordVerify() {
		return passwordVerify;
	}
}
