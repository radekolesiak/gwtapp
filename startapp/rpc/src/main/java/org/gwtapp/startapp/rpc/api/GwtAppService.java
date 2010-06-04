package org.gwtapp.startapp.rpc.api;

import org.gwtapp.core.shared.exception.RpcException;
import org.gwtapp.startapp.rpc.data.user.register.UserRegisterModel;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("gwtapp.rpc")
public interface GwtAppService extends RemoteService {
	void register(UserRegisterModel user) throws RpcException;
	void feedback(String from, String feedback) throws RpcException;
}
