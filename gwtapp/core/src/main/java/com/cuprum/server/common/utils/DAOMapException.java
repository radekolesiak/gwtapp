package com.cuprum.server.common.utils;

public class DAOMapException extends RuntimeException {

	/**
	 * UID.
	 */
	private static final long serialVersionUID = 6996946057725119336L;

	public DAOMapException() {
	}
	
	public DAOMapException(Throwable nested) {
		super(nested);
	}
}
