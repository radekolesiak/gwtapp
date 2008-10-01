package com.cuprum.server.common.model.user;

import com.cuprum.server.common.entities.User;
import com.cuprum.server.common.entities.UserSession;
import com.cuprum.server.common.model.IModel;

public interface IUserLoginModel extends IModel {
	User getUser(String login);

	User getUser(String login, String password);

	UserSession login(User user);

	UserSession login(String login, String password);

	void logout(String session);

	void logout(UserSession session);

	boolean existsLogin(User user);

	boolean existsLogin(String login);
}
