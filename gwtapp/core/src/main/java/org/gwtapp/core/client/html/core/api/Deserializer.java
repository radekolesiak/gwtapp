package org.gwtapp.core.client.html.core.api;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.SerializationException;
import com.google.gwt.user.client.rpc.SerializationStreamFactory;

public class Deserializer {

	public static native String getString(String name) /*-{
		return eval("$wnd."+name);
	}-*/;

	@SuppressWarnings("unchecked")
	public static <T> T getSerializedObject(String serialized)
			throws SerializationException {
		SerializationStreamFactory ssf = GWT.create(HTMLService.class);
		return (T) ssf.createStreamReader(serialized).readObject();
	}
}
