package com.cuprum.server.common.model.user.xql;

import java.sql.SQLException;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.HibernateCallback;

import com.cuprum.server.common.entities.User;
import com.cuprum.web.common.client.Util;

public class XqlMail implements HibernateCallback {
	private String mail;

	public XqlMail(final String mail) {
		this.mail = mail;
	}

	public String getMail() {
		return mail;
	}

	public Criteria getCriteria(final Session session) {
		return session.createCriteria(User.class).add(
				Restrictions.eq(User.MAIL, getMail()));
	}

	public User doInHibernate(Session session) throws HibernateException,
			SQLException {
		
		return Util.getFirst(getCriteria(session).list());
	}
}
