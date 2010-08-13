package org.gwtapp.io.server;

import java.io.Serializable;
import java.lang.reflect.Method;

import org.apache.commons.lang.StringEscapeUtils;
import org.gwtapp.io.rpc.api.IOService;

import com.google.gwt.user.client.rpc.IsSerializable;
import com.google.gwt.user.server.rpc.RPC;

public class IOServer {

	public static String success(IsSerializable object)
			throws IOServerException {
		try {
			Method serviceMethod = IOService.class
					.getMethod("getResponseIsSerializable");
			assert (serviceMethod != null);
			return RPC.encodeResponseForSuccess(serviceMethod, object);
		} catch (Exception e) {
			throw new IOServerException(e);
		}
	}

	public static String success(Serializable object) throws IOServerException {
		try {
			Method serviceMethod = IOService.class
					.getMethod("getResponseSerializable");
			assert (serviceMethod != null);
			return RPC.encodeResponseForSuccess(serviceMethod, object);
		} catch (Exception e) {
			throw new IOServerException(e);
		}
	}

	public static String failure(Throwable object) throws IOServerException {
		try {
			Method serviceMethod = IOService.class
					.getMethod("getResponseIsSerializable");
			assert (serviceMethod != null);
			assert (object != null);
			return RPC.encodeResponseForFailure(serviceMethod, object);
		} catch (Exception e) {
			throw new IOServerException(e);
		}
	}

	public static String encode(String s) {
		return StringEscapeUtils.escapeHtml(s);
	}
}
