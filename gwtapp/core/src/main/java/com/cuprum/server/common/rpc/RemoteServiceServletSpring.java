package com.cuprum.server.common.rpc;

import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.cuprum.server.common.utils.IDAO;
import com.cuprum.server.common.utils.RemoteServiceDAOMap;
import com.google.gwt.user.client.rpc.IsSerializable;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.SerializationException;
import com.google.gwt.user.server.rpc.RPC;
import com.google.gwt.user.server.rpc.RPCRequest;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

public class RemoteServiceServletSpring extends RemoteServiceServlet {

	private static Logger LOGGER = Logger
			.getLogger(RemoteServiceServletSpring.class);

	/**
	 * UID.
	 */
	private static final long serialVersionUID = -7179809962226883713L;

	private final static RemoteServiceDAOMap remoteServiceDAOMap = new RemoteServiceDAOMap();

	protected synchronized IDAO<RemoteService> getRemoteServiceDAO(
			final HttpServletRequest request) {
		return remoteServiceDAOMap.getDAO(RemoteServiceSession
				.getModuleName(request));
	}

	public final <T extends RemoteService> T getRpcBean(
			final HttpServletRequest request, final Class<T> c) {
		return getRemoteServiceDAO(request).getBean(c);
	}

	protected String getClassName(final HttpServletRequest request) {
		final String suffix = ".rpc";
		String[] s = request.getServletPath().split("/");
		if (s.length > 0) {
			String q = s[s.length - 1];
			if (q.endsWith(suffix)) {
				return q.substring(0, q.length() - suffix.length());
			}
		}
		return null;
	}

	protected void setRequestResponse(final RemoteService bean) {
		if (bean instanceof RemoteServiceSession) {
			RemoteServiceSession session = (RemoteServiceSession) bean;
			session.setRequest(getThreadLocalRequest());
			session.setResponse(getThreadLocalResponse());
		}
	}

	protected void unsetRequestResponse(final RemoteService bean) {
		if (bean instanceof RemoteServiceSession) {
			RemoteServiceSession session = (RemoteServiceSession) bean;
			session.setRequest(null);
			session.setResponse(null);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public String processCall(String payload) throws SerializationException {
		Method method = null;
		try {

			Class c = Class.forName(getClassName(getThreadLocalRequest()));

			RemoteService bean = getRpcBean(getThreadLocalRequest(), c);

			try {
				setRequestResponse(bean);

				RPCRequest rpcRequest = RPC.decodeRequest(payload, bean
						.getClass());

				method = rpcRequest.getMethod();

				return RPC.invokeAndEncodeResponse(bean, method, rpcRequest
						.getParameters());
			} finally {
				unsetRequestResponse(bean);
			}
		} catch (Exception e) {
			LOGGER.error("", e);
			if (e instanceof IsSerializable) {
				return RPC.encodeResponseForFailure(method, e);
			} else {
				return RPC.encodeResponseForFailure(method,
						new SpringRpcException());
			}
		}
	}
}
