package org.gwtapp.startapp.client.api;

import org.gwtapp.core.client.exception.RpcException;
import org.gwtapp.startapp.client.data.UserRegisterModel;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("gwtapp.rpc")
public interface GwtAppService extends RemoteService {
	void register(UserRegisterModel user) throws RpcException;
}
