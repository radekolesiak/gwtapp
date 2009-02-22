package com.cuprum.server.common.model.usersession;

import org.apache.log4j.Logger;

import com.cuprum.server.common.entities.UserSession;
import com.cuprum.server.common.model.Model;
import com.cuprum.server.common.model.usersession.xql.XqlCleanUserSessions;
import com.cuprum.server.common.model.usersession.xql.XqlGetUserSession;
import com.cuprum.server.common.model.usersession.xql.XqlGetUserSessionWithUser;

public class UserSessionModel extends Model implements IUserSessionModel {
	Logger LOGGER = Logger.getLogger(UserSessionModel.class);

	public void cleanSessions() {
		execute(new XqlCleanUserSessions());
	}

	public UserSession getSession(String session) {
		return (UserSession) execute(new XqlGetUserSession(session));
	}

	public UserSession getSessionWithUser(String session) {
		return (UserSession) execute(new XqlGetUserSessionWithUser(session));
	}
}
