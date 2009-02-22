package com.cuprum.server.common.model.user.xql;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.cuprum.server.common.entities.User;
import com.cuprum.server.common.model.xql.XqlValue;

public class XqlPassword extends XqlValue<String, User> {
	public XqlPassword(String value) {
		super(value);
	}

	@Override
	public Criteria getCriteria(Session session) {
		return session.createCriteria(User.class).add(
				Restrictions.eq(User.PASSWORD, getValue()));
	}
}
