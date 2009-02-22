package com.cuprum.server.common.model.usersession.xql;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.HibernateCallback;

import com.cuprum.server.common.entities.UserSession;
import com.cuprum.web.common.client.Util;
import com.cuprum.web.common.client.exceptions.usersession.SessionNotFoundException;

public class XqlGetUserSession implements HibernateCallback {
	private String sessionName;

	public XqlGetUserSession(final String sessionName) {
		this.sessionName = sessionName;
	}

	public String getSessionName() {
		return sessionName;
	}

	public Criteria getCriteria(final Session session) {
		return session.createCriteria(UserSession.class).add(
				Restrictions.eq(UserSession.SESSION, getSessionName()));
	}

	public UserSession doInHibernate(final Session session)
			throws HibernateException, SQLException, SessionNotFoundException {

		// remove old sessions
		new XqlCleanUserSessions().doInHibernate(session);

		List list = getCriteria(session).list();

		if (list.size() == 0) {
			throw new SessionNotFoundException("There is no '"
					+ getSessionName() + "' session");
		}

		UserSession userSession = Util.getFirst(list);
		Date now = new Date();

		// updating not too often

		if (Util.isUserSessionToUpdate(userSession.getDate().getTime(), now.getTime())) {
			userSession.setDate(now);
			session.update(userSession);
		}

		return userSession;
	}
}
