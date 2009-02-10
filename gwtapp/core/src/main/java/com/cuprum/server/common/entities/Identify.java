package com.cuprum.server.common.entities;

/**
 * Abstract class for hibernate tables with ID column.
 * 
 * @author Radek Olesiak
 * 
 */
public abstract class Identify {
	/** ID. */
	private Long id;

	public final static String ID = "id";

	/**
	 * Returns id.
	 * 
	 * @return id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * Sets id only by Hibernate.
	 * 
	 * @param id
	 *            Id to set.
	 */
	protected void setId(final Long id) {
		this.id = id;
	}
}
