package com.cuprum.web.common.client;

/**
 * 
 * Common constants for serwer and client sites.
 * 
 * @author radek
 * 
 */
public class Constants {
	/** Session request value for the url query. */
	public static final String USER_SESSION_REQUEST = "uid";

	/** Session request value for the url query. */
	public static final String CONNECTION_SESSION_REQUEST = "sid";

	/**
	 * User session inactivity time out in miliseconds. TODO: to make this
	 * configurable.
	 */
	public static final long USER_SESSION_TIME_OUT = 15L * 60L * 1000L;

	/** User session update frequency in miliseconds */
	public final static long USER_SESSION_UPDATE_TIMER = 5000L;

	public final static String INVALID_FIELD_STYLE = "invalid_field";

	public final static String WEAK_INVALID_FIELD_STYLE = "weak_invalid_field";

	public final static String VALID_FIELD_STYLE = "";

	/** Default contructor. */
	protected Constants() {
	}
}
