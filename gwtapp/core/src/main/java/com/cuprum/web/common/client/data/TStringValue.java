package com.cuprum.web.common.client.data;

import com.cuprum.web.common.client.convert.IConverter;
import com.cuprum.web.common.client.convert.StringConverter;

public class TStringValue extends TValue<String> implements IConverter<String> {
	/**
	 * UID.
	 */
	private static final long serialVersionUID = 5499043580047131085L;

	private final StringConverter converter = new StringConverter();

	public String convert(Object o) {
		return value = converter.convert(o);
	}

	public TStringValue() {
		value = "";
	}
}
