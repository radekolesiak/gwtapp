package com.cuprum.server.common.model.property.xql;

import java.sql.SQLException;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.HibernateCallback;

import com.cuprum.server.common.entities.Property;
import com.cuprum.web.common.client.Util;

public class XqlGetProperty implements HibernateCallback {
	private String property;

	public XqlGetProperty(final String property) {
		this.property = property;
	}

	public String getProperty() {
		return property;
	}

	public Criteria getCriteria(final Session session) {
		return session.createCriteria(Property.class).add(
				Restrictions.eq(Property.PROPERTY, getProperty()));
	}

	public Property doInHibernate(Session session) throws HibernateException,
			SQLException {
		return Util.getFirst(getCriteria(session).list());
	}
}
