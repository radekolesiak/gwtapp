package org.gwtapp.core.client.html.core;

import org.gwtapp.core.client.html.core.api.HTMLService;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.SerializationException;
import com.google.gwt.user.client.rpc.SerializationStreamFactory;

public class Deserializer {

	@SuppressWarnings("unchecked")
	public static <T> T getSerializedObject(String serialized)
			throws SerializationException {
		SerializationStreamFactory ssf = GWT.create(HTMLService.class);
		return (T) ssf.createStreamReader(serialized).readObject();
	}
}
