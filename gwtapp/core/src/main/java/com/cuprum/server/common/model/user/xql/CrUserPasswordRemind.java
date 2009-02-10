package com.cuprum.server.common.model.user.xql;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.cuprum.server.common.entities.UserPasswordRemind;
import com.cuprum.server.common.model.xql.CrValue;


public class CrUserPasswordRemind extends CrValue<String, UserPasswordRemind> {
	public CrUserPasswordRemind(final String value) {
		super(value);
	}

	public Criteria getCriteria(final Session session) {
		return session.createCriteria(UserPasswordRemind.class).add(
				Restrictions.eq(UserPasswordRemind.UID, getValue()));
	}
}
