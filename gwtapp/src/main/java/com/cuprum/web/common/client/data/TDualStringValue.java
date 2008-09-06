package com.cuprum.web.common.client.data;

/** GWT probably doesn't support well a String[] generics */
public class TDualStringValue extends TDualValue<String> {
	/**
	 * UID.
	 */
	private static final long serialVersionUID = -1300841148512044567L;

	public TDualStringValue() {
		value = "";
		second = "";
	}
}
