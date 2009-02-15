package com.cuprum.server.common.entities;

import java.util.Date;

/**
 * Sessions.
 * 
 * @author Radek Olesiak
 * 
 */
public class UserConfirm extends Identify {
	/** UID name. */
	private String uid;

	public final static String UID = "uid";

	/** Last time session activity. */
	private Date date;

	public final static String DATE = "date";

	/** User assigned with session. */
	private User user;

	public final static String USER = "user";

	private String password;

	public final static String PASSWORD = "password";

	public void setUid(final String uid) {
		this.uid = uid;
	}

	public String getUid() {
		return uid;
	}

	public void setDate(final Date date) {
		this.date = date;
	}

	public Date getDate() {
		return date;
	}

	public void setUser(final User user) {
		this.user = user;
	}

	public User getUser() {
		return user;
	}

	/**
	 * @param password
	 *            the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}
}
