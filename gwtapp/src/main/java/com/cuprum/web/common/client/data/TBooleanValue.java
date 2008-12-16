package com.cuprum.web.common.client.data;

import com.cuprum.web.common.client.convert.BooleanConverter;
import com.cuprum.web.common.client.convert.IConverter;

public class TBooleanValue extends TValue<Boolean> implements
		IConverter<Boolean> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7102526267806837150L;

	private final BooleanConverter converter = new BooleanConverter();

	public Boolean convert(Object o) {
		return value = converter.convert(o);
	}

	@Override
	public Boolean get() {
		return convert(value);
	}
}
