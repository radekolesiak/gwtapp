package com.cuprum.server.common.model.user.xql;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.cuprum.server.common.entities.User;
import com.cuprum.server.common.model.xql.CrValue;

public class CrPassword extends CrValue<String> {
	public CrPassword(String value) {
		super(value);
	}

	@Override
	public Criteria getCriteria(Session session) {
		return session.createCriteria(User.class).add(
				Restrictions.eq(User.PASSWORD, getValue()));
	}
}
