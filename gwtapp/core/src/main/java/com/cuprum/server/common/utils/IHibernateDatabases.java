package com.cuprum.server.common.utils;

/**
 * The list of databases.
 * @author radek
 * TODO: replace by interface with getDatabase method
 */
public interface IHibernateDatabases {
	/**
	 * Sets database configuration name. 
	 * @return Database configuration name.
	 */
	String getDatabase();
}