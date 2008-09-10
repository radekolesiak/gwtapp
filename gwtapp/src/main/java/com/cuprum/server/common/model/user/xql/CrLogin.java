package com.cuprum.server.common.model.user.xql;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.cuprum.server.common.entities.User;
import com.cuprum.server.common.model.xql.CrValue;

public class CrLogin extends CrValue<String> {
	public CrLogin(final String value) {
		super(value);
	}

	public Criteria getCriteria(final Session session) {
		return session.createCriteria(User.class).add(
				Restrictions.eq(User.LOGIN, getValue()));
	}
}
