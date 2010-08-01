package org.gwtapp.rest;

import java.lang.reflect.Method;

import org.apache.log4j.Logger;

import com.google.gwt.user.client.rpc.RemoteService;

public class RestEngineImpl implements RestEngine {

	private final static Logger log = Logger.getLogger(RestEngineImpl.class);

	private RemoteService remoteService;

	@Override
	public String process(String methodName, String[] inArgs, String[] inTypes) {
		try {
			if (inArgs.length == inTypes.length) {
				Object[] args = new Object[inArgs.length];
				Class<?>[] types = new Class[inTypes.length];
				for (int i = 0; i < inArgs.length; i++) {
					types[i] = Class.forName(inTypes[i]);
					args[i] = isNull(inArgs[i]) ? null : RestUtils
							.decode(inArgs[i].getBytes());
				}
				Method method = remoteService.getClass().getMethod(methodName,
						types);
				return new String(RestUtils.encode(method.invoke(remoteService,
						args)));
			}
		} catch (Exception e) {
			log.error("", e);
			return onFailure(methodName, e);
		}
		return onFailure(methodName, new IllegalStateException());
	}

	@Override
	public String onFailure(String methodName, Exception e) {
		return "Exception: " + e.getClass().getName();
	}

	public void setRemoteService(RemoteService remoteService) {
		this.remoteService = remoteService;
	}

	public RemoteService getRemoteService() {
		return remoteService;
	}

	private boolean isNull(String arg) {
		return arg.equalsIgnoreCase("NULL");
	}
}
