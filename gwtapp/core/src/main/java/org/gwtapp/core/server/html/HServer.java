package org.gwtapp.core.server.html;

import java.io.Serializable;
import java.lang.reflect.Method;

import org.apache.commons.codec.binary.Base64;
import org.gwtapp.core.client.html.core.api.HTMLService;

import com.google.gwt.user.client.rpc.IsSerializable;
import com.google.gwt.user.server.rpc.RPC;
import com.google.gwt.user.server.rpc.RPCRequest;

public class HServer {

	public static <T> T getSerializedObject(String object) {
		RPCRequest request = RPC.decodeRequest(object);
		if (request != null && request.getParameters() != null
				&& request.getParameters().length > 0) {
			System.out.println(request.getParameters()[0]);
		}
		return null;
	}

	public static String success(IsSerializable object) throws HServerException {
		try {
			Method serviceMethod = HTMLService.class
					.getMethod("getResponseIsSerializable");
			assert (serviceMethod != null);
			assert (object != null);
			return RPC.encodeResponseForSuccess(serviceMethod, object);
		} catch (Exception e) {
			throw new HServerException(e);
		}
	}

	public static String success(Serializable object) throws HServerException {
		try {
			Method serviceMethod = HTMLService.class
					.getMethod("getResponseSerializable");
			assert (serviceMethod != null);
			assert (object != null);
			return RPC.encodeResponseForSuccess(serviceMethod, object);
		} catch (Exception e) {
			throw new HServerException(e);
		}
	}

	public static String failure(Throwable object) throws HServerException {
		try {
			Method serviceMethod = HTMLService.class
					.getMethod("getResponseIsSerializable");
			assert (serviceMethod != null);
			assert (object != null);
			return RPC.encodeResponseForFailure(serviceMethod, object);
		} catch (Exception e) {
			throw new HServerException(e);
		}
	}

	public static String encode(String s) {
		return new String(Base64.encodeBase64(s.getBytes()));
	}

}
