package com.cuprum.server.common.utils;

import com.cuprum.server.common.model.IModel;

public class HibernateDAO extends AbstractDAO<IModel> {
	protected String getClassPath() {
		return "/com/cuprum/server/config/db/bean-hibernate-" + getName()
				+ ".cfg.xml";
	}
	
	@SuppressWarnings("unchecked")
	public <C extends IModel> C getBean(Class<C> c) {
		IModel model = super.getBean(c);
		if(model != null) {
			model.setDAO(this);
		}
		return (C) model;
	}
}
