package com.cuprum.server.common.utils;

import org.springframework.context.support.AbstractRefreshableApplicationContext;

public interface IDAO <T> {
	/**
	 * Application context for a servlet 'RPC' methods. All objects wich
	 * derivates from com.cuprum.server.common.model.Model must be created by
	 * beans. <code>
	 * InheritsModel object = dao.getBean(InheritsModel.class);
	 * </code>.
	 */
	AbstractRefreshableApplicationContext getApplicationContext();

	<C extends T> C getBean(Class<C> c);
	
	String getName();
	
	void setupContext(String database);
	
	void setupContext(HibernateDatabases database);
	
	void close();
}
