package com.cuprum.server.common.utils;

import org.springframework.context.support.AbstractRefreshableApplicationContext;

public interface IDAO <T> {
	/**
	 * Application context for a servlet 'RPC' methods. All objects wich
	 * inherit from com.cuprum.server.common.model.Model must be created by
	 * beans. <code>
	 * InheritsModel object = dao.getBean(InheritsModel.class);
	 * </code>.
	 */
	AbstractRefreshableApplicationContext getApplicationContext();

	<C extends T> C getBean(Class<C> c);
	
	void setupContext(String database);
	
	void close();
}
