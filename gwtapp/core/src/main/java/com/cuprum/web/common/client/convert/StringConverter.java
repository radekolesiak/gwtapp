package com.cuprum.web.common.client.convert;

public class StringConverter implements IConverter<String> {

	public String convert(Object o) {
		if (o == null) {
			return null;
		} else {
			return o.toString();
		}
	}
	
}
