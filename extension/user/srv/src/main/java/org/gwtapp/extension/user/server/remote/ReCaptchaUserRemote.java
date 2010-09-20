package org.gwtapp.extension.user.server.remote;

import org.gwtapp.core.rpc.exception.RpcException;
import org.gwtapp.extension.user.client.api.ReCaptchaUserService;
import org.gwtapp.extension.user.client.data.ReCaptchaUser;
import org.gwtapp.extension.user.client.data.exception.ReCaptchaUserValidationException;
import org.gwtapp.extension.user.client.data.exception.ReCaptchaUserValidationException.ReCaptcha;
import org.gwtapp.extension.user.client.data.exception.UserValidationException;
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
		assert reCaptchaPrivateKey != null;
		try {
			addReCaptchaUserValidate(user);
			return userAddService.addUser(user);
		} catch (UserValidationException userValidation) {
			ReCaptchaUserValidationException validation = new ReCaptchaUserValidationException();
			validation.setUserValidationException(userValidation);
			throw validation;
		}
	}

	private void addReCaptchaUserValidate(ReCaptchaUser user) {
		assert userAddService != null;
		assert reCaptchaVerify != null;
		ReCaptchaUserValidationException validation = new ReCaptchaUserValidationException();
		try {
			userAddService.addUserValidate(user);
		} catch (UserValidationException userValidationException) {
			validation.setUserValidationException(userValidationException);
		}
		if (!reCaptchaVerify.verify(user, reCaptchaPrivateKey.getPrivateKey(),
				getThreadLocalRequest().getRemoteAddr())) {
			validation.addReCaptcha(ReCaptcha.INVALID);
		}
		validation.validate();
	}
}
