package org.gwtapp.extension.user.client.api;

import org.gwtapp.core.rpc.exception.RpcException;
import org.gwtapp.extension.user.client.data.UserImpl;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("extension.user.rpc")
public interface UserService extends RemoteService {
	UserImpl getUser(String login) throws RpcException;
}
