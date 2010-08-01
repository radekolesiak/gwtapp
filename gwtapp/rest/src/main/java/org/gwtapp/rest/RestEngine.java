package org.gwtapp.rest;

import com.google.gwt.user.client.rpc.RemoteService;

public interface RestEngine {
	String process(String methodName, String[] inArgs, String[] inTypes);
	String onFailure(String methodName, Exception e);
	RemoteService getRemoteService();
	void setRemoteService(RemoteService remoteService);
}
