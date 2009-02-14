package com.cuprum.server.common.model.user;

import com.cuprum.server.common.entities.User;
import com.cuprum.server.common.entities.UserPasswordRemind;
import com.cuprum.server.common.entities.UserSession;
import com.cuprum.server.common.model.IModel;
import com.cuprum.web.common.client.data.TDualValue;
import com.cuprum.web.common.client.data.TValue;
import com.cuprum.web.widgets.user.password.client.data.TChangePasswordByToken;

public interface IUserPasswordModel extends IModel {
	void verifyRemindPasswordToken(TValue<String> token);

	void verifyOldPassword(UserSession session, TValue<String> password);

	void verifyOldPassword(User user, TValue<String> password);

	void verifyNewPasswordSingle(TValue<String> password);

	void verifyNewPasswordDual(TDualValue<String> password);

	void updatePasswordByUser(UserSession session, TValue<String> password);

	void updatePasswordByUser(User user, TValue<String> password);

	UserPasswordRemind getPasswordRemindToken(String login);

	UserPasswordRemind getPasswordRemindToken(User user);

	UserPasswordRemind getUserPasswordRemind(String token);

	void updatePasswordByToken(TChangePasswordByToken password);

}
