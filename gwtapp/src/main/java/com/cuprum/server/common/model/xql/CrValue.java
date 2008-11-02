package com.cuprum.server.common.model.xql;

import java.sql.SQLException;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;

import com.cuprum.web.common.client.Util;

public abstract class CrValue<T, R> implements HibernateCallback {
	private T value;

	public CrValue(final T value) {
		this.value = value;
	}

	public T getValue() {
		return value;
	}

	@SuppressWarnings("unchecked")
	public R doInHibernate(Session session) throws HibernateException,
			SQLException {
		return (R) Util.getFirst(getCriteria(session).list());
	}

	public abstract Criteria getCriteria(final Session session);
}
