package com.cuprum.server.common.model.xql;

import java.sql.SQLException;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;

import com.cuprum.web.common.client.Util;

public abstract class CrValue<T> implements HibernateCallback {
	private T value;

	public CrValue(final T value) {
		this.value = value;
	}

	public T getValue() {
		return value;
	}

	public Object doInHibernate(Session session) throws HibernateException,
			SQLException {
		return Util.getFirst(getCriteria(session).list());
	}

	public abstract Criteria getCriteria(final Session session);
}
