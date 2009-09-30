package org.gwtapp.core.server.html;

import java.lang.reflect.Method;

import org.apache.commons.codec.binary.Base64;
import org.gwtapp.core.client.html.core.api.HTMLService;
import org.gwtapp.core.client.html.ui.core.HWidget;

import com.google.gwt.user.server.rpc.RPC;

public class HSerializer {

	public static String serialize(HWidget widget) throws HSerializerException {
		try {
			Method serviceMethod = HTMLService.class.getMethod("getWidget",
					String.class);
			assert (serviceMethod != null);
			assert (widget != null);
			return RPC.encodeResponseForSuccess(serviceMethod, widget);
		} catch (Exception e) {
			throw new HSerializerException(e);
		}
	}

	public static String encode(String s) {
		return new String(Base64.encodeBase64(s.getBytes()));
	}

}
