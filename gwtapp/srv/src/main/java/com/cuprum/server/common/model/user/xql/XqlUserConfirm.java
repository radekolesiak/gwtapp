package com.cuprum.server.common.model.user.xql;

import java.sql.SQLException;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.HibernateCallback;

import com.cuprum.server.common.entities.UserConfirm;
import com.cuprum.web.common.client.Util;

public class XqlUserConfirm implements HibernateCallback {
	private String uid;

	public XqlUserConfirm(final String uid) {
		this.uid = uid;
	}

	public String getUid() {
		return uid;
	}

	public Criteria getCriteria(Session session) {
		return session.createCriteria(UserConfirm.class).add(
				Restrictions.eq(UserConfirm.UID, getUid()));
	}

	public UserConfirm doInHibernate(Session session) throws HibernateException,
			SQLException {
		return Util.getFirst(getCriteria(session).list());
	}
}
