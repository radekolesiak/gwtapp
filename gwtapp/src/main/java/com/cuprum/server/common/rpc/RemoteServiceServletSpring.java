package com.cuprum.server.common.rpc;

import org.apache.log4j.Logger;

import com.google.gwt.user.client.rpc.IncompatibleRemoteServiceException;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.SerializationException;
import com.google.gwt.user.server.rpc.RPC;
import com.google.gwt.user.server.rpc.RPCRequest;

public class RemoteServiceServletSpring extends RemoteServiceServletSession
		implements RemoteService {

	private static Logger LOGGER = Logger
			.getLogger(RemoteServiceServletSpring.class);

	/**
	 * UID.
	 */
	private static final long serialVersionUID = -7179809962226883713L;

	@Override
	public String processCall(String payload) throws SerializationException {
		try {
			LOGGER.info("RPC bean: " + getClass().getName());

			RemoteService bean = getRpcBean(getClass());

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
			LOGGER.error(e);
			return RPC.encodeResponseForFailure(null, e);
		}
	}

}
