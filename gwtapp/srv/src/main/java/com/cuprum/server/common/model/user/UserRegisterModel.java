package com.cuprum.server.common.model.user;

import java.util.Date;

import org.apache.log4j.Logger;
import org.springframework.dao.DataIntegrityViolationException;

import com.cuprum.server.common.entities.User;
import com.cuprum.server.common.entities.UserConfirm;
import com.cuprum.server.common.model.Model;
import com.cuprum.server.common.model.user.xql.XqlLogin;
import com.cuprum.server.common.model.user.xql.XqlUserConfirm;
import com.cuprum.server.common.model.user.xql.XqlUserToConfirm;
import com.cuprum.server.common.utils.Random;
import com.cuprum.web.common.client.HasRegExp;
import com.cuprum.web.common.client.Util;
import com.cuprum.web.common.client.data.TValue;
import com.cuprum.web.common.client.exceptions.RegExpException;
import com.cuprum.web.common.client.exceptions.TextToShortException;
import com.cuprum.web.common.client.exceptions.model.user.UserAlreadyExistsException;
import com.cuprum.web.widgets.user.register.client.data.TUserRegisterLoginValue;
import com.cuprum.web.widgets.user.register.client.data.TUserRegisterValue;

public class UserRegisterModel extends Model implements IUserRegisterModel {
	/**
	 * Logger for this class.
	 */
	static final Logger LOGGER = Logger.getLogger(UserRegisterModel.class);

	public boolean isToConfirm(User user) {
		return Util.isNotNull(execute(new XqlUserToConfirm(user)));
	}

	public boolean isToConfirm(String login) {
		User user = execute(new XqlLogin(login));
		if (Util.isNotNull(user)) {
			return isToConfirm(user);
		} else {
			return false;
		}
	}

	public UserConfirm register(TUserRegisterValue userRegister) {
		if (!errorsUserRegister(userRegister)) {
			// If there are not error to try insert user into database.
			User user = new User();
			user.setLogin(userRegister.login.get());
			user.setPassword(userRegister.password.get());
			user.setMail(userRegister.mail.get());
			try {
				save(user);
				UserConfirm confirm = new UserConfirm();
				confirm.setDate(new Date());
				confirm.setUid(Random.getUid());
				confirm.setUser(user);
				confirm.setPassword(user.getPassword());
				user.setPassword(Random.getUid());
				update(user);
				save(confirm);
				return confirm;
			} catch (DataIntegrityViolationException e) {
				// Probably threads concurency problem. Check it again.
				if (!errorsUserRegister(userRegister)) {
					// Unidentified problem. To throw upper the exception.
					LOGGER.error(e);
					throw e;
				}
			}
		}
		return null;
	}

	public void verifyNewLogin(TValue<String> login) {
		IUserLoginModel modelLogin = getDAO().getBean(IUserLoginModel.class);

		if (login instanceof HasRegExp
				&& !((HasRegExp) login).match(login.value)) {
			login.error = new RegExpException();
		} else if (login.value.length() < TUserRegisterLoginValue.MIN_LOGIN_LENGTH) {
			login.error = new TextToShortException();
		} else if (modelLogin.existsLogin(login.value)) {
			login.error = new UserAlreadyExistsException();
		}
	}

	public boolean errorsUserRegister(TUserRegisterValue userRegister) {
		IUserPasswordModel modelPassword = getDAO().getBean(
				IUserPasswordModel.class);
		IUserMailModel modelMail = getDAO().getBean(IUserMailModel.class);

		userRegister.clearErrors();

		verifyNewLogin(userRegister.getValueLogin());
		modelPassword.verifyNewPasswordDual(userRegister.getValuePassword());
		modelMail.verifyNewMail(userRegister.getValueMail());

		return userRegister.hasErrors();
	}

	public boolean confirm(String uid) {
		UserConfirm confirm = execute(new XqlUserConfirm(uid));
		if (confirm != null) {
			User user = get(User.class, confirm.getUser().getId());
			user.setPassword(confirm.getPassword());
			update(user);
			delete(confirm);
			return user != null;
		} else {
			return false;
		}
	}
}
