package com.cuprum.server.common.entities;

import java.util.Date;

/**
 * Sessions.
 * 
 * @author Radek Olesiak
 * 
 */
public class UserSession extends Identify {
	/** Session name. */
	private String session;

	public final static String SESSION = "session";

	/** Last time session activity. */
	private Date date;

	public final static String DATE = "date";

	/** User assigned with session. */
	private User user;

	public final static String USER = "user";

	public void setSession(final String session) {
		this.session = session;
	}

	public String getSession() {
		return session;
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
}
