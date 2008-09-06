package com.cuprum.utils;

import com.cuprum.server.common.utils.HibernateDatabases;

/**
 * The list of testing databases.
 * @author radek
 * TODO: replace by interface with getDatabase method
 */
public enum ToTestHibernateDatabases implements HibernateDatabases {
	/** The general testing database configuration. */
	TEST("test");

	/** Database configuration.*/
	private String database;

	/**
	 * Sets database configuration name.
	 * @param aDatabase Database configuration name.
	 */
	ToTestHibernateDatabases(final String aDatabase) {
		this.database = aDatabase;
	}

	/**
	 * Sets database configuration name.
	 * 
	 * @return Database configuration name.
	 */
	public String getDatabase() {
		return database;
	}
}
