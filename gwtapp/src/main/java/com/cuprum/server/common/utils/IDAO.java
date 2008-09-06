package com.cuprum.server.common.utils;

import org.springframework.context.support.AbstractRefreshableApplicationContext;

import com.cuprum.server.common.model.Model;

public interface IDAO {
	/**
	 * Application context for a servlet 'RPC' methods. All objects wich
	 * derivates from com.cuprum.server.common.model.Model must be created by
	 * beans. <code>
	 * InheritsModel object = dao.getBean(InheritsModel.class);
	 * </code>.
	 */
	AbstractRefreshableApplicationContext getApplicationContext();

	<T extends Model> T getBean(Class<T> c);
	
	String getName();
	
	void setupContext(String database);
	
	void setupContext(HibernateDatabases database);
	
	void close();
}
