package com.cuprum.server.common.model.usersession.xql;

import java.sql.SQLException;
import java.util.Date;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;

import com.cuprum.server.common.entities.UserSession;
import com.cuprum.web.common.client.Constants;
import com.cuprum.web.common.client.Util;

public class CrCleanUserSessions implements HibernateCallback {
	public Query getQuery(Session session) {
		Date now = new Date();
		return session.createQuery("delete from UserSession where "
				+ UserSession.DATE + " < " + now.getTime() / 1000 + " - "
				+ Constants.USER_SESSION_TIME_OUT / 1000);
	}

	private long lastUpdate = 0L;

	public Object doInHibernate(Session session) throws HibernateException,
			SQLException {

		Date now = new Date();
		if (Util.isToUpdate(lastUpdate, now.getTime())) {
			lastUpdate = now.getTime();
			return getQuery(session).executeUpdate();
		} else {
			return false;
		}
	}
}
