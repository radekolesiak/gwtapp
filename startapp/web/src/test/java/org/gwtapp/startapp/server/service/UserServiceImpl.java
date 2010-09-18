package org.gwtapp.startapp.server.service;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.RollbackException;

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
import org.gwtapp.extension.user.server.stub.UserAddService;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.Singleton;
import com.wideplay.warp.persist.Transactional;

@Singleton
public class UserServiceImpl implements UserService, UserAddService {

	private static final Logger log = Logger.getLogger(UserServiceImpl.class);

	@Inject
	Provider<EntityManager> em;

	@Override
	public User getUser(String login) throws RpcException {
		log.warn("Not implemented");
		throw new NotImplementedException();
	}

	@Override
	public long addUser(UserPassword up) throws RpcException {
		UserValidationException validation = new UserValidationException();
		if (StringUtils.isEmpty(up.getUser().getLogin())) {
			validation.addLogin(Login.EMPTY);
		} else {
			if (up.getUser().getLogin()
					.matches(UserValidationException.ANY_UPPER_CASE_REGEXP)) {
				validation.addLogin(Login.NOT_LOWER_CASE);
			}
			if (!up.getUser().getLogin()
					.matches(UserValidationException.ONLY_LETTERS_REGEXP)) {
				validation.addLogin(Login.NOT_LETTERS_ONLY);
			}
			if (up.getUser().getLogin().length() < 3) {
				validation.addLogin(Login.TOO_SHORT);
			}
		}
		if (StringUtils.isEmpty(up.getUser().getEmail())) {
			validation.addEmail(Email.EMPTY);
		} else if (!up.getUser().getEmail()
				.matches(UserValidationException.EMAIL_REGEXP)) {
			validation.addEmail(Email.INVALID);
		}
		validation.validate();
		try {
			persistUser(up.getUser());
		} catch (RollbackException e) {
			if (e.getCause() instanceof EntityExistsException) {
				// TODO determine which one already exist
				validation.addLogin(Login.ALREADY_EXISTS);
				validation.addEmail(Email.ALREADY_EXISTS);
				validation.validate();
			} else {
				throw e;
			}
		}
		log.debug("persisted id=" + up.getUser().getId());
		return up.getUser().getId();
	}

	@Transactional
	protected void persistUser(User user) {
		em.get().persist(user);
	}

}
