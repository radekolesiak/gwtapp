package org.gwtapp.core.server.html;

import java.lang.reflect.Method;

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
}
