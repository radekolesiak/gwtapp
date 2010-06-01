package org.gwtapp.core.client.io;

import com.google.gwt.http.client.RequestBuilder;
import com.google.gwt.user.client.rpc.RpcRequestBuilder;
import com.google.gwt.user.client.rpc.ServiceDefTarget;

public class IORpcRequestBuilder extends RpcRequestBuilder {

	public static void updateService(ServiceDefTarget service) {
		service.setRpcRequestBuilder(new IORpcRequestBuilder());
	}

	@Override
	protected RequestBuilder doCreate(String serviceEntryPoint) {
		return new IORequestBuilder(RequestBuilder.POST, serviceEntryPoint);
	}

}