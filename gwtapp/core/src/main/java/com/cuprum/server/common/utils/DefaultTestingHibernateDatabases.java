package com.cuprum.server.common.utils;


/**
 * The list of testing databases.
 * @author radek
 * TODO: replace by interface with getDatabase method
 */
public enum DefaultTestingHibernateDatabases implements IHibernateDatabases {
	/** The general testing database configuration. */
	TEST("test");

	/** Database configuration.*/
	private String database;

	/**
	 * Sets database configuration name.
	 * @param aDatabase Database configuration name.
	 */
	DefaultTestingHibernateDatabases(final String aDatabase) {
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
