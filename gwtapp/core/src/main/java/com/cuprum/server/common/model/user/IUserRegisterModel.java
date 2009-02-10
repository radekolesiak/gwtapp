package com.cuprum.server.common.model.user;

import com.cuprum.server.common.entities.User;
import com.cuprum.server.common.entities.UserConfirm;
import com.cuprum.server.common.model.IModel;
import com.cuprum.web.common.client.data.TValue;
import com.cuprum.web.widgets.user.register.client.data.TUserRegisterValue;

public interface IUserRegisterModel extends IModel {
	boolean isToConfirm(User user);

	boolean isToConfirm(String login);

	UserConfirm register(TUserRegisterValue userRegister);

	void verifyNewLogin(TValue<String> login);

	boolean errorsUserRegister(TUserRegisterValue userRegister);

	boolean confirm(String uid);
}
