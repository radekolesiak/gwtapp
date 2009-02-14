package com.cuprum.server.common.model.user.xql;

import java.sql.SQLException;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.HibernateCallback;

import com.cuprum.server.common.entities.User;
import com.cuprum.server.common.model.usersession.xql.CrCleanUserSessions;
import com.cuprum.web.common.client.Util;

public class CrLoginPassword implements HibernateCallback {
	private String login;

	private String password;

	public CrLoginPassword(final String login, final String password) {
		this.login = login;
		this.password = password;
	}

	public String getLogin() {
		return login;
	}

	public String getPassword() {
		return password;
	}

	public Criteria getCriteria(final Session session) {
		return new CrLogin(getLogin()).getCriteria(session).add(
				Restrictions.eq(User.PASSWORD, getPassword()));
	}

	public User doInHibernate(Session session) throws HibernateException,
			SQLException {

		new CrCleanUserSessions().doInHibernate(session);

		return Util.getFirst(getCriteria(session).list());
	}
}
