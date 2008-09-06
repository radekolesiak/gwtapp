package com.cuprum.server.common.entities;

/**
 * Common table USER.
 * 
 * @author Radek Olesiak
 * 
 */

public class User extends Identify {
	/** Login field. */
	private String login;

	public final static String LOGIN = "login";

	/** Password field. */
	private String password;

	public final static String PASSWORD = "password";

	/** Mail field. */
	private String mail;

	public final static String MAIL = "mail";

	/**
	 * Sets login.
	 * 
	 * @param login
	 *            Login.
	 */
	public void setLogin(final String login) {
		this.login = login;
	}

	/**
	 * Returns login.
	 * 
	 * @return Login.
	 */
	public String getLogin() {
		return login;
	}

	/**
	 * Sets password.
	 * 
	 * @param password
	 *            Password
	 */
	public void setPassword(final String password) {
		this.password = password;
	}

	/**
	 * Returns password.
	 * 
	 * @return Password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param mail the mail to set
	 */
	public void setMail(String mail) {
		this.mail = mail;
	}

	/**
	 * @return the mail
	 */
	public String getMail() {
		return mail;
	}
}
