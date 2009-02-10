package com.cuprum.server.smallapp.mainapp.rpc;

import java.util.Date;

import org.apache.log4j.Logger;

import com.cuprum.server.common.entities.UserSession;
import com.cuprum.server.common.model.usersession.IUserSessionModel;
import com.cuprum.server.common.rpc.RemoteServiceUserSession;
import com.cuprum.web.common.client.Util;
import com.cuprum.web.common.client.exceptions.model.usersession.SessionNotFoundException;
import com.cuprum.web.smallapp.mainapp.client.stub.ISmallApp;

/**
 * 
 * @author Radek Olesiak
 * 
 */

public class SmallAppRpc extends RemoteServiceUserSession implements
		ISmallApp {

	private static Logger LOGGER = Logger.getLogger(SmallAppRpc.class);

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
