package com.cuprum.web.common.client.data;

import com.cuprum.web.common.client.convert.IConverter;
import com.cuprum.web.common.client.convert.LongConverter;

public class TLongValue extends TValue<Long> implements IConverter<Long> {

	/**
	 * UID.
	 */
	private static final long serialVersionUID = 7102526267806837150L;

	private final LongConverter converter = new LongConverter();

	public Long convert(Object o) {
		return value = converter.convert(o);
	}

	@Override
	public Long get() {
		return convert(value);
	}
}
