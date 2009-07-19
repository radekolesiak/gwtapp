package org.gwtapp.startapp.client.data;

import java.util.HashMap;

import com.google.gwt.user.client.rpc.IsSerializable;

@SuppressWarnings("serial")
public class TestData extends HashMap<String, Integer> implements
		IsSerializable {
	public TestData getName() {
		return new TestData();
	}
}
