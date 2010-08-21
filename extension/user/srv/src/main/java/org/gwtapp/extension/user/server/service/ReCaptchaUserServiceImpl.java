package org.gwtapp.extension.user.server.service;

import org.gwtapp.core.rpc.exception.RpcException;
import org.gwtapp.extension.user.client.api.ReCaptchaUserService;
import org.gwtapp.extension.user.client.data.ReCaptchaUserImpl;
import org.gwtapp.extension.user.server.stub.UserAddStub;

public class ReCaptchaUserServiceImpl implements ReCaptchaUserService {

	private UserAddStub userAddService = new UserServiceImpl();

	@Override
	public long addUser(ReCaptchaUserImpl user) throws RpcException {
		// TODO verify reCaptcha first
		return userAddService.addUser(user);
	}
}
