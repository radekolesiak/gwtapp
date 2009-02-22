package com.cuprum.server.common.model.user.xql;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.cuprum.server.common.entities.User;
import com.cuprum.server.common.entities.UserConfirm;
import com.cuprum.server.common.model.xql.XqlValue;

public class XqlUserToConfirm extends XqlValue<User, UserConfirm> {
	public XqlUserToConfirm(final User value) {
		super(value);
	}

	public Criteria getCriteria(final Session session) {
		return session.createCriteria(UserConfirm.class).add(
				Restrictions.eq(UserConfirm.USER, getValue()));
	}
}
