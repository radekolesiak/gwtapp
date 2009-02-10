package com.cuprum.server.common.rpc;

import java.io.Serializable;
import java.lang.reflect.Method;

import org.apache.log4j.Logger;

import com.google.gwt.user.client.rpc.IsSerializable;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.SerializationException;
import com.google.gwt.user.server.rpc.RPC;
import com.google.gwt.user.server.rpc.RPCRequest;

public class RemoteServiceServletSpring extends RemoteServiceServletSession {

	private static Logger LOGGER = Logger
			.getLogger(RemoteServiceServletSpring.class);

	/**
	 * UID.
	 */
	private static final long serialVersionUID = -7179809962226883713L;

	protected String getClassName() {
		final String suffix = ".rpc";
		String[] s = getThreadLocalRequest().getServletPath().split("/");
		if (s.length > 0) {
			String q = s[s.length - 1];
			if (q.endsWith(suffix)) {
				return q.substring(0, q.length() - suffix.length());
			}
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public String processCall(String payload) throws SerializationException {
		Method method = null;
		try {

			Class c = Class.forName(getClassName());

			RemoteService bean = getRpcBean(c);

			if (bean instanceof RemoteServiceServletSession) {
				RemoteServiceServletSession session = (RemoteServiceServletSession) bean;
				session.setRequest(getThreadLocalRequest());
				session.setResponse(getThreadLocalResponse());
			}

			RPCRequest rpcRequest = RPC.decodeRequest(payload, bean.getClass());

			method = rpcRequest.getMethod();

			return RPC.invokeAndEncodeResponse(bean, method, rpcRequest
					.getParameters());
		} catch (Exception e) {
			LOGGER.error("", e);
			if (e instanceof IsSerializable || e instanceof Serializable) {
				return RPC.encodeResponseForFailure(method, e);
			} else {
				return RPC.encodeResponseForFailure(method,
						new SpringRpcException());
			}
		}
	}
}
