package org.gwtapp.extension.user.server;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.gwtapp.core.rpc.exception.NotImplementedException;
import org.gwtapp.core.rpc.exception.RpcException;
import org.gwtapp.extension.user.client.api.UserService;
import org.gwtapp.extension.user.client.data.User;
import org.gwtapp.extension.user.client.data.exception.UserValidationException;
import org.gwtapp.extension.user.client.data.exception.UserValidationException.Email;
import org.gwtapp.extension.user.client.data.exception.UserValidationException.Login;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

public class UserServiceImpl extends RemoteServiceServlet implements
		UserService {

	private static final Logger log = Logger.getLogger(UserServiceImpl.class);

	private static final long serialVersionUID = -7438675123588513834L;

	@Override
	public User getUser(String login) throws RpcException {
		log.warn("Not implemented");
		throw new NotImplementedException();
	}

	//@Override
	public long addUser(User user) throws RpcException {
		UserValidationException validation = new UserValidationException();
		if (StringUtils.isEmpty(user.getLogin())) {
			validation.setLogin(Login.INVALID);
		} else if (user.getLogin().matches("[A-Z]+")) {
			validation.setLogin(Login.NOT_LOWER_CASE);
		} else if (!user.getLogin().matches("[a-z]+")) {
			validation.setLogin(Login.NOT_LETTERS_ONLY);
		} else if (user.getLogin().length() < 3) {
			validation.setLogin(Login.TOO_SHORT);
		}
		if (validation.getLogin() != Login.VALID
				|| validation.getEmail() != Email.VALID) {
			throw validation;
		}
		log.warn("Not implemented");
		throw new NotImplementedException();
	}
}
