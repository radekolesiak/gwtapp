package org.gwtapp.extension.user.server.remote.service;

import org.gwtapp.core.rpc.exception.RpcException;
import org.gwtapp.extension.user.client.api.UserService;
import org.gwtapp.extension.user.client.data.User;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.google.inject.Inject;
import com.google.inject.Singleton;

@SuppressWarnings("serial")
@Singleton
public class UserServiceRemote extends RemoteServiceServlet implements
		UserService {

	@Inject
	private UserService userService;

	@Override
	public User getUser(String login) throws RpcException {
		assert userService != null;
		return userService.getUser(login);
	}
}
