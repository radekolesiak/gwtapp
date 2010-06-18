package org.gwtapp.io.rpc;

import org.gwtapp.io.rpc.api.IOService;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.SerializationException;
import com.google.gwt.user.client.rpc.SerializationStreamFactory;
import com.google.gwt.user.client.rpc.SerializationStreamWriter;

public class IOClient {

	/*
	 * http://www.webtoolkit.info/javascript-base64.html
	 * http://www.webtoolkit.info/licence.html
	 */

	private final static SerializationStreamFactory ssf = GWT
			.create(IOService.class);

	public static String getSerializedString(Object object)
			throws SerializationException {
		SerializationStreamWriter ssw = ssf.createStreamWriter();
		ssw.writeObject(object);
		return ssw.toString();
	}

	@SuppressWarnings("unchecked")
	public static <T> T getDeserializedObject(String serialized)
			throws SerializationException {
		return (T) ssf.createStreamReader(serialized).readObject();
	}

	private final static String KEY_STR = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/=";

	private static native String decodeBase64(String input, String _keyStr)/*-{
																			var output = "";
																			var chr1, chr2, chr3;
																			var enc1, enc2, enc3, enc4;
																			var i = 0;
																			
																			input = input.replace(/[^A-Za-z0-9\+\/\=]/g, "");
																			
																			while (i < input.length) {
																			
																			enc1 = _keyStr.indexOf(input.charAt(i++));
																			enc2 = _keyStr.indexOf(input.charAt(i++));
																			enc3 = _keyStr.indexOf(input.charAt(i++));
																			enc4 = _keyStr.indexOf(input.charAt(i++));
																			
																			chr1 = (enc1 << 2) | (enc2 >> 4);
																			chr2 = ((enc2 & 15) << 4) | (enc3 >> 2);
																			chr3 = ((enc3 & 3) << 6) | enc4;
																			
																			output = output + String.fromCharCode(chr1);
																			
																			if (enc3 != 64) {
																			output = output + String.fromCharCode(chr2);
																			}
																			if (enc4 != 64) {
																			output = output + String.fromCharCode(chr3);
																			}
																			
																			}
																			
																			return output;	
																			}-*/;

	public static String decode(String t) {
		return decodeBase64(t, KEY_STR);
	}

}
