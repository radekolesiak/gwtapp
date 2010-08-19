package org.gwtapp.extension.user.server.test;

import java.util.List;

import javax.persistence.Query;

import org.apache.commons.lang.StringUtils;
import org.gwtapp.core.rpc.exception.RpcException;
import org.gwtapp.extension.user.client.api.ReCaptchaUserService;
import org.gwtapp.extension.user.client.api.UserService;
import org.gwtapp.extension.user.client.data.User;
import org.gwtapp.extension.user.client.data.exception.UserValidationException;
import org.gwtapp.extension.user.client.data.exception.UserValidationException.Email;
import org.gwtapp.extension.user.client.data.exception.UserValidationException.Login;

@SuppressWarnings("serial")
public class UserServiceTestImpl extends RemoteServiceDBServlet implements
		UserService, ReCaptchaUserService {

	@SuppressWarnings("unchecked")
	@Override
	public User getUser(String login) throws RpcException {
		if (login == null) {
			throw new RpcException();
		} else if ("aba".equals(login)) {
			User user = new User();
			user.setId(1L);
			user.setLogin(login);
			user.setEmail(login + "@email.com");
			user.setName("Name: " + login);
			return user;
		} else if ("xyz".equals(login)) {
			return null;
		} else {
			Query query = getEntityManager().createQuery(
					"SELECT u FROM CCalcUser u WHERE u.login = ?1");
			query.setParameter(1, login);
			List<User> users = query.getResultList();
			if (users.isEmpty()) {
				throw new RpcException();
			} else {
				return users.get(0);
			}
		}
	}

	@Override
	public long addUser(User user) throws RpcException {
		UserValidationException validation = new UserValidationException();
		if ("aba".equalsIgnoreCase(user.getLogin())) {
			validation.setLogin(Login.ALREADY_EXISTS);
		} else if ("xyz".equalsIgnoreCase(user.getLogin())) {
			validation.setLogin(Login.ALREADY_EXISTS);
		} else if (StringUtils.isEmpty(user.getLogin())) {
			validation.setLogin(Login.INVALID);
		} else if (user.getLogin().matches(
				UserValidationException.ANY_UPPER_CASE_REGEXP)) {
			validation.setLogin(Login.NOT_LOWER_CASE);
		} else if (!user.getLogin().matches(
				UserValidationException.ONLY_LETTERS_REGEXP)) {
			validation.setLogin(Login.NOT_LETTERS_ONLY);
		} else if (user.getLogin().length() < 3) {
			validation.setLogin(Login.TOO_SHORT);
		}
		if ("aba@email.com".equalsIgnoreCase(user.getEmail())) {
			validation.setEmail(Email.ALREADY_EXISTS);
		} else if (StringUtils.isEmpty(user.getEmail())) {
			validation.setEmail(Email.INVALID);
		} else if (!user.getEmail().matches(
				UserValidationException.EMAIL_REGEXP)) {
			validation.setEmail(Email.INVALID);
		}
		if (validation.getLogin() != Login.VALID
				|| validation.getEmail() != Email.VALID) {
			throw validation;
		}
		return 0;
	}
}
