package com.cuprum.web.smallapp.mainapp.rpc;


// TODO Remove this class for proxy and connector to the database

public class SmallAppRpc {}
/*
extends RemoteServiceServletUserSession implements
		ISmallApp {

	private static final long serialVersionUID = -5462277142458843488L;

	public Date getLastSessionActivity() throws SessionNotFoundException {
		UserSessionModel model = getBean(UserSessionModel.class);
		UserSession session = model.getSession(getUserSession());
		if (Util.isNotNull(session)) {
			return session.getDate();
		} else {
			return null;
		}
	}

	public void forceRemoveSession() throws SessionNotFoundException {
		UserSessionModel model = getBean(UserSessionModel.class);
		UserSession session = model.getSession(getUserSession());
		if (Util.isNotNull(session)) {
			model.delete(session);
		}
	}
}
*/
