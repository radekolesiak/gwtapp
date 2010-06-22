package org.gwtapp.startapp.rpc.api;

import org.gwtapp.core.rpc.exception.RpcException;
import org.gwtapp.startapp.rpc.data.user.register.UserRegisterModel;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("gwtapp.rpc")
public interface StartAppService extends RemoteService {
	void register(UserRegisterModel user) throws RpcException;
	void feedback(String from, String feedback) throws RpcException;
}
