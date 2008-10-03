package com.cuprum.server.common.utils;

import java.util.HashMap;

import org.apache.log4j.Logger;

public abstract class DAOMap<T> {
	private static Logger LOGGER = Logger.getLogger(DAOMap.class);
	
	private final HashMap<String, IDAO<T>> daos = new HashMap<String, IDAO<T>>();

	public synchronized IDAO<T> getDAO(final String name) {
		if (!daos.containsKey(name)
				|| !((IDAO<T>) daos.get(name)).getApplicationContext()
						.isActive()) {
			LOGGER.info("Creating DAO for: " + name);
			IDAO<T> dao = createDAO();
			dao.setupContext(name);
			daos.put(name, dao);
		}
		return daos.get(name);
	}
	
	abstract protected IDAO<T> createDAO();
}
