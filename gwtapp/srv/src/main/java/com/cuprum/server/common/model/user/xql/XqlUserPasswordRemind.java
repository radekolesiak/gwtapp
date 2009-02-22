package com.cuprum.server.common.model.user.xql;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.cuprum.server.common.entities.UserPasswordRemind;
import com.cuprum.server.common.model.xql.XqlValue;


public class XqlUserPasswordRemind extends XqlValue<String, UserPasswordRemind> {
	public XqlUserPasswordRemind(final String value) {
		super(value);
	}

	public Criteria getCriteria(final Session session) {
		return session.createCriteria(UserPasswordRemind.class).add(
				Restrictions.eq(UserPasswordRemind.UID, getValue()));
	}
}
