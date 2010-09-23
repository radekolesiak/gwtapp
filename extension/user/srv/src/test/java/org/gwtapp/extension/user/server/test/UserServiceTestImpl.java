package org.gwtapp.extension.user.server.test;

import java.util.List;

import javax.persistence.Query;

import org.apache.commons.lang.StringUtils;
import org.gwtapp.core.rpc.exception.RpcException;
import org.gwtapp.extension.user.client.api.ReCaptchaUserService;
import org.gwtapp.extension.user.client.api.UserService;
import org.gwtapp.extension.user.client.data.ReCaptchaUser;
import org.gwtapp.extension.user.client.data.User;
import org.gwtapp.extension.user.client.data.UserImpl;
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
			User user = new UserImpl();
			user.setId(1L);
			user.setLogin(login);
			user.setEmail(login + "@email.com");
			return user;
		} else if ("xyz".equals(login)) {
			return null;
		} else {
			String q = "SELECT u FROM UserEntity u WHERE u.login = :login";
			Query query = getEntityManager().createQuery(q);
			query.setMaxResults(1);
			query.setParameter("login", login);
			List<UserImpl> users = query.getResultList();
			if (users.isEmpty()) {
				throw new RpcException();
			} else {
				return users.get(0);
			}
		}
	}

	@Override
	public long addReCaptchaUser(ReCaptchaUser user) throws RpcException {
		UserValidationException validation = new UserValidationException();
		if ("aba".equalsIgnoreCase(user.getUser().getLogin())) {
			validation.addLogin(Login.ALREADY_EXISTS);
		} else if ("xyz".equalsIgnoreCase(user.getUser().getLogin())) {
			validation.addLogin(Login.ALREADY_EXISTS);
		} else if (StringUtils.isEmpty(user.getUser().getLogin())) {
			validation.addLogin(Login.EMPTY);
		} else if (user.getUser().getLogin()
				.matches(UserValidationException.ANY_UPPER_CASE_REGEXP)) {
			validation.addLogin(Login.NOT_LOWER_CASE);
		} else if (!user.getUser().getLogin()
				.matches(UserValidationException.ONLY_LETTERS_REGEXP)) {
			validation.addLogin(Login.NOT_LETTERS_ONLY);
		} else if (user.getUser().getLogin().length() < 3) {
			validation.addLogin(Login.TOO_SHORT);
		}
		if ("aba@email.com".equalsIgnoreCase(user.getUser().getEmail())) {
			validation.addEmail(Email.ALREADY_EXISTS);
		} else if (StringUtils.isEmpty(user.getUser().getEmail())) {
			validation.addEmail(Email.EMPTY);
		} else if (!user.getUser().getEmail()
				.matches(UserValidationException.EMAIL_REGEXP)) {
			validation.addEmail(Email.EMPTY);
		}
		validation.validate();
		return 0;
	}
}
