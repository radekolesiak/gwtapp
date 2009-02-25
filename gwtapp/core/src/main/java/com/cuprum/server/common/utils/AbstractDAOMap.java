package com.cuprum.server.common.utils;

import java.util.HashMap;

import org.apache.log4j.Logger;

public abstract class AbstractDAOMap<T> {
	private static Logger LOGGER = Logger.getLogger(AbstractDAOMap.class);

	private final HashMap<String, IDAO<T>> daos = new HashMap<String, IDAO<T>>();

	@SuppressWarnings("unchecked")
	public synchronized <Z extends IDAO<T>> Z getDAO(final String name)
			throws DAOMapException {
		if (!daos.containsKey(name)) {
			LOGGER.info("Creating DAO for: " + name);
			IDAO<T> dao = null;
			try {
				dao = createDAO();
				dao.setupContext(name);
				daos.put(name, dao);
			} catch (Exception e) {
				throw new DAOMapException(e);
			}
		}
		if (daos.get(name) == null) {
			throw new DAOMapException();
		} else {
			return (Z) daos.get(name);
		}
	}

	abstract protected IDAO<T> createDAO();
}
