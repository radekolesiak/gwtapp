package org.gwtapp.rest;

import java.lang.reflect.Method;

import org.apache.log4j.Logger;

import com.google.gwt.user.client.rpc.RemoteService;

public class RestEngineImpl implements RestEngine {

	private final static Logger log = Logger.getLogger(RestEngineImpl.class);

	private RemoteService remoteService;

	@Override
	public String process(String methodName, String xmlArgs) {
		try {
			Object[] args = RestUtils.decode(xmlArgs.getBytes());
			Class<?>[] argsTypes = new Class[args.length];
			for (int i = 0; i < args.length; i++) {
				argsTypes[i] = args[i].getClass();
			}
			Method method=remoteService.getClass().getMethod(methodName, argsTypes);
			method.invoke(remoteService, args);
		} catch (Exception e) {
			log.error("", e);
			return onFailure(methodName, e);
		}
		return onNull(methodName);
	}

	@Override
	public String onFailure(String methodName, Exception e) {
		return onNull(methodName);
	}

	@Override
	public String onNull(String methodName) {
		return "NULL";
	}

	public void setRemoteService(RemoteService remoteService) {
		this.remoteService = remoteService;
	}

	public RemoteService getRemoteService() {
		return remoteService;
	}
}
