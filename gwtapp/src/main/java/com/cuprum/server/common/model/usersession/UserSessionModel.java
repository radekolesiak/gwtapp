package com.cuprum.server.common.model.usersession;

import org.apache.log4j.Logger;

import com.cuprum.server.common.entities.UserSession;
import com.cuprum.server.common.model.Model;
import com.cuprum.server.common.model.usersession.xql.CrGetUserSession;
import com.cuprum.server.common.model.usersession.xql.CrGetUserSessionWithUser;

public class UserSessionModel extends Model {
	Logger LOGGER = Logger.getLogger(UserSessionModel.class);

	public UserSession getSession(String session) {
		return (UserSession) execute(new CrGetUserSession(session));
	}

	public UserSession getSessionWithUser(String session) {
		return (UserSession) execute(new CrGetUserSessionWithUser(session));
	}
}
