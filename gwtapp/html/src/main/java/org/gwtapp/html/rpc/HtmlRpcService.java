package org.gwtapp.html.rpc;

import org.gwtapp.io.rpc.IOClient;

import com.google.gwt.i18n.client.Dictionary;

public class HtmlRpcService {

	public final static String DICTIONARY = "RpcValues";

	private final Dictionary values;

	public HtmlRpcService() {
		this(DICTIONARY);
	}

	public HtmlRpcService(String dictionary) {
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
				return IOClient.getDeserializedObject(IOClient.decode(value));
			}
		} catch (Exception e) {
		}
		return null;
	}
}
