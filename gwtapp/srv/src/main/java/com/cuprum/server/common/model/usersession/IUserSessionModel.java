package com.cuprum.server.common.model.usersession;

import com.cuprum.server.common.entities.UserSession;
import com.cuprum.server.common.model.IModel;

public interface IUserSessionModel extends IModel {
	void cleanSessions();

	UserSession getSession(String session);

	UserSession getSessionWithUser(String session);
}
