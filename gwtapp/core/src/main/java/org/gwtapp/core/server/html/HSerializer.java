package org.gwtapp.core.server.html;

import java.lang.reflect.Method;

import org.gwtapp.core.client.html.core.api.HTMLService;
import org.gwtapp.core.client.html.ui.HWidget;

import com.google.gwt.user.client.rpc.SerializationException;
import com.google.gwt.user.server.rpc.RPC;

public class HSerializer {

	public String serialize(HWidget widget) throws SerializationException,
			SecurityException, NoSuchMethodException {
		Method serviceMethod = HTMLService.class.getMethod("getWidget",
				String.class);
		return RPC.encodeResponseForSuccess(serviceMethod, widget,
				new HSerializationPolicy());
	}
}
