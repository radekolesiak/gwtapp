package org.gwtapp.core.client.data;

import com.google.gwt.user.client.rpc.IsSerializable;

public class A implements IsSerializable {

	private String value;

	public void setValue(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
}
