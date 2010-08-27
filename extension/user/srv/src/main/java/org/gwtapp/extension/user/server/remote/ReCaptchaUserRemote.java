package org.gwtapp.extension.user.server.remote;

import org.gwtapp.core.rpc.exception.RpcException;
import org.gwtapp.extension.user.client.api.ReCaptchaUserService;
import org.gwtapp.extension.user.client.data.ReCaptchaUser;
import org.gwtapp.extension.user.server.service.UserServiceImpl;
import org.gwtapp.extension.user.server.stub.UserAddStub;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

@SuppressWarnings("serial")
public class ReCaptchaUserRemote extends RemoteServiceServlet implements
		ReCaptchaUserService {

	private UserAddStub userAddService = new UserServiceImpl();

	@Override
	public long addUser(ReCaptchaUser user) throws RpcException {
		// TODO verify reCaptcha first
		return userAddService.addUser(user);
	}
}
