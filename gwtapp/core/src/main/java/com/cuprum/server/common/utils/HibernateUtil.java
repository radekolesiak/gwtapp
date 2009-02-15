package com.cuprum.server.common.utils;

import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

/**
 * 
 * @author radek
 * 
 * Loads configuration by the HibernateDatabases enum and allows to set as
 * default.
 * 
 */
public final class HibernateUtil {
	/** Logger for this class. */
	static final Logger LOGGER = Logger.getLogger(HibernateUtil.class);

	/** Hides the default constructor. */
	private HibernateUtil() {
	}

	/**
	 * UID.
	 */
	private static final long serialVersionUID = -3896177133727192618L;

	/**
	 * SessionFactory is a thread-safe global object, instantiated once.
	 * <p>
	 * A SessionFactory can open up new Session's, a Session represents a
	 * single-threaded unit of work.
	 */
	private static SessionFactory defaultSession = null;

	/**
	 * Returns the singleton instance of the hibernate {@link SessionFactory}.
	 * 
	 * @return {@link SessionFactory}
	 */
	public static synchronized SessionFactory getDefaultSessionFactory() {
		return defaultSession;
	}

	/**
	 * Sets current default database.
	 * 
	 * @param database
	 *            Database to set as current default.
	 */
	public static synchronized void setDefaultSessionFactory(
			final IHibernateDatabases database) {
		clearDefaultSessionFactory();
		defaultSession = getSessionFactory(database);
	}

	public static synchronized void clearDefaultSessionFactory() {
		SessionFactory factory = getDefaultSessionFactory();
		if (factory != null && !factory.isClosed()) {
			factory.close();
		}
	}

	/**
	 * Loads configuration and returns database.
	 * 
	 * @param database
	 *            Database to load.
	 * @return Database.
	 */
	public static synchronized SessionFactory getSessionFactory(
			final IHibernateDatabases database) {
		try {
			// Create the SessionFactory from hibernate.cfg.xml
			AnnotationConfiguration ac = new AnnotationConfiguration();
			// FRA: this also "starts" hibernate
			ac.configure(
					"com/cuprum/server/config/db/hibernate-" + database.getDatabase()
							+ ".cfg.xml").buildSettings();

			LOGGER.debug("Hibernate has loaded the configuration: "
					+ database.getDatabase());

			return ac.buildSessionFactory();
		} catch (Throwable ex) {
			LOGGER.error("Hibernate couldn't to load the configuration: "
					+ database.getDatabase(), ex);

			throw new ExceptionInInitializerError(ex);
		}
	}
}
