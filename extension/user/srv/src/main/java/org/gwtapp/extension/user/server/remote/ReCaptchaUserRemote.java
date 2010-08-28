package org.gwtapp.extension.user.server.remote;

import org.gwtapp.core.rpc.exception.RpcException;
import org.gwtapp.extension.user.client.api.ReCaptchaUserService;
import org.gwtapp.extension.user.client.data.ReCaptchaUser;
import org.gwtapp.extension.user.server.stub.UserAddStub;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.google.inject.Inject;
import com.google.inject.Singleton;

@SuppressWarnings("serial")
@Singleton
public class ReCaptchaUserRemote extends RemoteServiceServlet implements
		ReCaptchaUserService {

	@Inject
	private UserAddStub userAddService;

	@Override
	public long addUser(ReCaptchaUser user) throws RpcException {
		assert userAddService != null;
		return userAddService.addUser(user);
	}
}
