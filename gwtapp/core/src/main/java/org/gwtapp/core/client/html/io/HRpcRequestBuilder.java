package org.gwtapp.core.client.html.io;

import com.google.gwt.http.client.RequestBuilder;
import com.google.gwt.user.client.rpc.RpcRequestBuilder;
import com.google.gwt.user.client.rpc.ServiceDefTarget;

public class HRpcRequestBuilder extends RpcRequestBuilder {

	public static void updateService(ServiceDefTarget service) {
		service.setRpcRequestBuilder(new HRpcRequestBuilder());
	}

	@Override
	protected RequestBuilder doCreate(String serviceEntryPoint) {
		return new HRequestBuilder(RequestBuilder.POST, serviceEntryPoint);
	}

}