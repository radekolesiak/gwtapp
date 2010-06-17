package org.gwtapp.io.server;

import java.io.Serializable;
import java.lang.reflect.Method;

import org.apache.commons.codec.binary.Base64;
import org.gwtapp.io.rpc.api.IOService;

import com.google.gwt.user.client.rpc.IsSerializable;
import com.google.gwt.user.server.rpc.RPC;
import com.google.gwt.user.server.rpc.RPCRequest;

public class IOServer {

	public static <T> T getSerializedObject(String object) {
		RPCRequest request = RPC.decodeRequest(object);
		if (request != null && request.getParameters() != null
				&& request.getParameters().length > 0) {
			System.out.println(request.getParameters()[0]);
		}
		return null;
	}

	public static String success(IsSerializable object) throws IOServerException {
		try {
			Method serviceMethod = IOService.class
					.getMethod("getResponseIsSerializable");
			assert (serviceMethod != null);
			assert (object != null);
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
			assert (object != null);
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
		return new String(Base64.encodeBase64(s.getBytes()));
	}

}
