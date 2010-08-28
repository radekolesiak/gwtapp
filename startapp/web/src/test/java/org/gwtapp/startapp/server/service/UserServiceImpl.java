package org.gwtapp.startapp.server.service;

import javax.persistence.EntityManager;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.gwtapp.core.rpc.exception.NotImplementedException;
import org.gwtapp.core.rpc.exception.RpcException;
import org.gwtapp.extension.user.client.api.UserService;
import org.gwtapp.extension.user.client.data.User;
import org.gwtapp.extension.user.client.data.UserPassword;
import org.gwtapp.extension.user.client.data.exception.UserValidationException;
import org.gwtapp.extension.user.client.data.exception.UserValidationException.Email;
import org.gwtapp.extension.user.client.data.exception.UserValidationException.Login;
import org.gwtapp.extension.user.server.stub.UserAddStub;

import com.google.inject.Inject;
import com.google.inject.Singleton;

@Singleton
public class UserServiceImpl implements UserService, UserAddStub {

	private static final Logger log = Logger.getLogger(UserServiceImpl.class);

	@Inject
	private EntityManager em;

	@Override
	public User getUser(String login) throws RpcException {
		log.warn("Not implemented");
		throw new NotImplementedException();
	}

	@Override
	public long addUser(UserPassword up) throws RpcException {
		UserValidationException validation = new UserValidationException();
		if (StringUtils.isEmpty(up.getUser().getLogin())) {
			validation.setLogin(Login.INVALID);
		} else if (up.getUser().getLogin().matches("[A-Z]+")) {
			validation.setLogin(Login.NOT_LOWER_CASE);
		} else if (!up.getUser().getLogin().matches("[a-z]+")) {
			validation.setLogin(Login.NOT_LETTERS_ONLY);
		} else if (up.getUser().getLogin().length() < 3) {
			validation.setLogin(Login.TOO_SHORT);
		}
		if (validation.getLogin() != Login.VALID
				|| validation.getEmail() != Email.VALID) {
			throw validation;
		}
		try {
			em.persist(up.getUser());
		} catch (RuntimeException e) {
			log.error("", e);
			throw e;
		}
		log.debug("persisted id="+up.getUser().getId());
		return up.getUser().getId();
	}
}
