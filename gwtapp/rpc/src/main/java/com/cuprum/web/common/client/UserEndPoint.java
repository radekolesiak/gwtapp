package com.cuprum.web.common.client;

import java.util.LinkedList;

import com.cuprum.web.common.client.EndPoint;
import com.cuprum.web.common.client.data.TUserSession;

public class UserEndPoint extends EndPoint {
	
	@Override
	protected LinkedList<String> getQueries() {
		LinkedList<String> list = super.getQueries();
		if (TUserSession.getSession() != null) {
			list.add(TUserSession.USER_SESSION_REQUEST + "="
					+ TUserSession.getSession().get());
		}
		return list;
	}

	private final static UserEndPoint userSessionEndPoint = new UserEndPoint();

	public static <T> T create(final T endpoint, final String service) {
		return userSessionEndPoint.get(endpoint, service);
	}

	public static <T> T create(final T endpoint) {
		return userSessionEndPoint.get(endpoint);
	}
}
