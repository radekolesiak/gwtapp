package org.gwtapp.io.client;


import com.google.gwt.i18n.client.Dictionary;

public class IOHtmlRpcDeserializer {

	public final static String DICTIONARY = "RpcValues";

	private final Dictionary values;

	public IOHtmlRpcDeserializer() {
		this(DICTIONARY);
	}

	public IOHtmlRpcDeserializer(String dictionary) {
		Dictionary values;
		try {
			values = Dictionary.getDictionary(dictionary);
		} catch (Exception e) {
			values = null;
		}
		this.values = values;
	}

	public Object getValue(String valueName) {
		try {
			String value = values.get(valueName);
			if (values != null && value != null) {
				return IOClient.deserialize(IOClient.decode(value));
			}
		} catch (Exception e) {
		}
		return null;
	}
}
