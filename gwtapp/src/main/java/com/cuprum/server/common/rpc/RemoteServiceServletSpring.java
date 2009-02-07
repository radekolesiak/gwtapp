package com.cuprum.server.common.rpc;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import com.google.gwt.user.client.rpc.IncompatibleRemoteServiceException;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.SerializationException;
import com.google.gwt.user.server.rpc.RPC;
import com.google.gwt.user.server.rpc.RPCRequest;

public class RemoteServiceServletSpring extends RemoteServiceServletSession {

	private static Logger LOGGER = Logger
			.getLogger(RemoteServiceServletSpring.class);

	static {
		LOGGER.setLevel(Level.ALL);
	}
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
		// TODO: fix this
		return "";
	}

	@Override
	public String processCall(String payload) throws SerializationException {
		try {

			Class c = Class.forName(getClassName());

			RemoteService bean = getRpcBean(c);

			LOGGER.info("RPC mapped: " + bean.getClass().getName());

			if (bean instanceof RemoteServiceServletSession) {
				RemoteServiceServletSession session = (RemoteServiceServletSession) bean;
				session.setRequest(getThreadLocalRequest());
				session.setResponse(getThreadLocalResponse());
			}

			try {
				RPCRequest rpcRequest = RPC.decodeRequest(payload, bean
						.getClass());

				// delegate work to the spring injected service
				return RPC.invokeAndEncodeResponse(bean,
						rpcRequest.getMethod(), rpcRequest.getParameters());
			} catch (IncompatibleRemoteServiceException e) {
				getServletContext()
						.log(
								"An IncompatibleRemoteServiceException was thrown while processing this call.",
								e);
				return RPC.encodeResponseForFailure(null, e);
			}
		} catch (Throwable e) {
			LOGGER.error("", e);
			return RPC.encodeResponseForFailure(null, e);
		}
	}
}
