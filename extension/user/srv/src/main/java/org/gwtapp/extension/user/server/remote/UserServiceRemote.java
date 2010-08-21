package org.gwtapp.extension.user.server.remote;

import org.gwtapp.core.rpc.exception.RpcException;
import org.gwtapp.extension.user.client.api.UserService;
import org.gwtapp.extension.user.client.data.UserImpl;
import org.gwtapp.extension.user.server.service.UserServiceImpl;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

@SuppressWarnings("serial")
public class UserServiceRemote extends RemoteServiceServlet implements
		UserService {

	private UserService userService = new UserServiceImpl();

	@Override
	public UserImpl getUser(String login) throws RpcException {
		return userService.getUser(login);
	}
}
