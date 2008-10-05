package com.cuprum.server.common.utils;

public interface IHibernateDAO<T> extends IDAO<T> {	
	void setupContext(HibernateDatabases database);
}
