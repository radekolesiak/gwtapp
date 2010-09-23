package org.gwtapp.startapp.server.service;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.Query;
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
import org.gwtapp.extension.user.server.local.stub.UserPasswordAdd;
import org.gwtapp.validation.rpc.exception.ValidationException;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.Singleton;
import com.wideplay.warp.persist.Transactional;

@Singleton
public class UserServiceImpl implements UserService, UserPasswordAdd {

	private static final Logger log = Logger.getLogger(UserServiceImpl.class);

	@Inject
	Provider<EntityManager> em;

	@Override
	public User getUser(String login) throws RpcException {
		log.warn("Not implemented");
		throw new NotImplementedException();
	}

	@Override
	public long addUserPassword(UserPassword up) throws RpcException {
		try {
			validateBeforeAddUserPassword(up);
			persistUser(up.getUser());
		} catch (RollbackException e) {
			if (e.getCause() instanceof EntityExistsException) {
				UserValidationException validation = new UserValidationException();
				if (!isLoginAvailable(up.getUser().getLogin())) {
					validation.addLogin(Login.ALREADY_EXISTS);
				}
				if (!isEmailAvailable(up.getUser().getEmail())) {
					validation.addEmail(Email.ALREADY_EXISTS);
				}
				validation.validate();
			} else {
				throw e;
			}
		}
		log.debug("persisted id=" + up.getUser().getId());
		return up.getUser().getId();
	}

	@Override
	public void validateBeforeAddUserPassword(UserPassword up)
			throws UserValidationException {
		UserValidationException validation = new UserValidationException();
		try {
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
				//if (!isLoginAvailable(up.getUser().getLogin())) {
					//validation.addLogin(Login.ALREADY_EXISTS);
				//}
			}
			if (StringUtils.isEmpty(up.getUser().getEmail())) {
				validation.addEmail(Email.EMPTY);
			} else {
				if (!up.getUser().getEmail()
						.matches(UserValidationException.EMAIL_REGEXP)) {
					validation.addEmail(Email.INVALID);
				}
				//if (!isEmailAvailable(up.getUser().getEmail())) {
				//	validation.addEmail(Email.ALREADY_EXISTS);
				//}
			}
			validation.validate();
		} catch (ValidationException e) {
			throw e;
		} catch (RuntimeException e) {
			log.error("", e);
			throw e;
		}
	}

	@Transactional
	protected void persistUser(User user) {
		em.get().persist(user);
	}

	//@Transactional
	protected boolean isLoginAvailable(String login) {
		String q = "SELECT u FROM UserEntity u WHERE u.login = ?1";
		Query query = em.get().createQuery(q);
		//query.setMaxResults(1);
		query.setParameter(1, login);
		return query.getResultList().isEmpty();
	}

	//@Transactional
	protected boolean isEmailAvailable(String email) {
		String q = "SELECT u FROM UserEntity u WHERE u.email = ?1";
		Query query = em.get().createQuery(q);
		//query.setMaxResults(1);
		query.setParameter(1, email);
		return query.getResultList().isEmpty();
	}
}
