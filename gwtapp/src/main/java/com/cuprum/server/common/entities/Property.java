package com.cuprum.server.common.entities;

public class Property extends Identify {

	/** Property name field. */
	private String property;

	public final static String PROPERTY = "property";

	/** Property value field. */
	private String value;

	public final static String VALUE = "value";

	/**
	 * @param property
	 *            the property to set
	 */
	public void setProperty(String property) {
		this.property = property;
	}

	/**
	 * @return the property
	 */
	public String getProperty() {
		return property;
	}

	/**
	 * @param login
	 *            the login to set
	 */
	public void setValue(String value) {
		this.value = value;
	}

	/**
	 * @return the login
	 */
	public String getValue() {
		return value;
	}

	public String toString() {
		return "" + value;
	}
}
