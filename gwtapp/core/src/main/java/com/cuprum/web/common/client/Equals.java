package com.cuprum.web.common.client;

/** 
 * Equals utils.
 * @author Radek Olesiak
 *
 */
public class Equals {
	/** Hides constructor. */
	protected Equals() {		
	}
	/**
	 * Compares two objects.
	 * @param <T> The type of arguments
	 * @param s1 First object to compare.
	 * @param s2 Second object to compare.
	 * @return The result of comparison.
	 */
	public static <T> boolean isEquals(final T s1, final T s2) {
		if (s1 == s2) {
			return true;
		}

		if (s1 == null) {
			return false;
		}

		return s1.equals(s2);
	}
}
