package com.cuprum.server.common.model.usersession.xql;

import java.sql.SQLException;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;

import com.cuprum.server.common.entities.User;
import com.cuprum.server.common.entities.UserSession;
import com.cuprum.web.common.client.Util;

public class CrGetUserSessionWithUser implements HibernateCallback {
	private String sessionName;

	public CrGetUserSessionWithUser(final String sessionName) {
		this.sessionName = sessionName;
	}

	public String getSessionName() {
		return sessionName;
	}

	public UserSession doInHibernate(final Session session)
			throws HibernateException, SQLException {

		UserSession sess = new CrGetUserSession(getSessionName())
				.doInHibernate(session);

		if (Util.isNotNull(sess)) {
			User user = (User) sess.getUser();
			sess.setUser((User) session.get(User.class, user.getId()));
		}

		return sess;
	}
}
