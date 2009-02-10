package com.cuprum.server.common.model.usersession.xql;

import org.springframework.orm.hibernate3.HibernateCallback;

import com.cuprum.server.common.entities.UserSession;

public abstract class UserSessionHibernateCallback implements HibernateCallback {
	private UserSession userSession;

	public UserSession getUserSession() {
		return userSession;
	}

	protected void setConnection(final UserSession userSession) {
		this.userSession = userSession;
	}

	public UserSessionHibernateCallback(UserSession userSession) {
		setConnection(userSession);
	}
}
