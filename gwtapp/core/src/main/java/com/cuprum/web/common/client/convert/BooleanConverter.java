package com.cuprum.web.common.client.convert;

public class BooleanConverter implements IConverter<Boolean> {
	
	public Boolean convert(Object o) {
		if (o == null) {
			return null;
		} else if (o instanceof Boolean) {
			return (Boolean) o;
		} else {
			return Boolean.parseBoolean(o.toString());
		}
	}
}
