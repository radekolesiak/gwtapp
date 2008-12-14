package com.cuprum.web.common.client.convert;

public class LongConverter implements IConverter<Long> {
	public Long convert(Object o) {
		if (o == null) {
			return null;
		} else if (o instanceof Long) {
			return (Long) o;
		} else {
			return Long.parseLong(o.toString());
		}
	}
}
