package com.cuprum.server.common.utils;

import com.cuprum.server.common.model.IModel;

public class HibernateDAOMap extends AbstractDAOMap<IModel> {
	public final static HibernateDAOMap DAO = new HibernateDAOMap();

	@Override
	protected HibernateDAO createDAO() {
		return new HibernateDAO();
	}
}
