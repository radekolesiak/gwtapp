package org.gwtapp.extension.user.server.remote;

import org.gwtapp.core.rpc.exception.RpcException;
import org.gwtapp.extension.user.client.api.ReCaptchaUserService;
import org.gwtapp.extension.user.client.data.ReCaptchaUser;
import org.gwtapp.extension.user.client.data.exception.ReCaptchaUserValidationException;
import org.gwtapp.extension.user.server.stub.ReCaptchaPrivateKey;
import org.gwtapp.extension.user.server.stub.ReCaptchaVerify;
import org.gwtapp.extension.user.server.stub.UserAdd;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.google.inject.Inject;
import com.google.inject.Singleton;

@SuppressWarnings("serial")
@Singleton
public class ReCaptchaUserRemote extends RemoteServiceServlet implements
		ReCaptchaUserService {

	@Inject
	private UserAdd userAddService;
	@Inject
	private ReCaptchaVerify reCaptchaVerify;
	@Inject
	private ReCaptchaPrivateKey reCaptchaPrivateKey;

	@Override
	public long addReCaptchaUser(ReCaptchaUser user) throws RpcException {
		assert userAddService != null;
		assert reCaptchaVerify != null;
		assert reCaptchaPrivateKey != null;
		if (!reCaptchaVerify.verify(user, reCaptchaPrivateKey.getPrivateKey(),
				getThreadLocalRequest().getRemoteAddr())) {
			throw new ReCaptchaUserValidationException();
		}
		return userAddService.addUser(user);
	}
}
