package com.cuprum.web.smallapp.mainapp.rpc;

import java.util.Date;

import org.apache.log4j.Logger;

import com.cuprum.server.common.entities.UserSession;
import com.cuprum.server.common.model.usersession.IUserSessionModel;
import com.cuprum.web.common.client.Util;
import com.cuprum.web.common.client.exceptions.model.usersession.SessionNotFoundException;
import com.cuprum.web.common.rpc.RemoteServiceServletUserSession;
import com.cuprum.web.smallapp.mainapp.client.stub.ISmallApp;

public class SmallAppDao extends RemoteServiceServletUserSession implements
		ISmallApp {

	private static Logger LOGGER = Logger.getLogger(SmallAppDao.class);

	/**
	 * UID
	 */
	private static final long serialVersionUID = -5462277142458843488L;

	public Date getLastSessionActivity() throws SessionNotFoundException {
		LOGGER.debug(getUserSession());
		IUserSessionModel model = getBean(IUserSessionModel.class);
		UserSession session = model.getSession(getUserSession());
		if (Util.isNotNull(session)) {
			return session.getDate();
		} else {
			return null;
		}
	}

	public void forceRemoveSession() throws SessionNotFoundException {
		IUserSessionModel model = getBean(IUserSessionModel.class);
		UserSession session = model.getSession(getUserSession());
		if (Util.isNotNull(session)) {
			model.delete(session);
		}
	}
}
