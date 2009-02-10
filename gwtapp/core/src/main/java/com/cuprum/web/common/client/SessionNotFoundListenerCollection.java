package com.cuprum.web.common.client;

import java.util.ArrayList;

import com.google.gwt.user.client.ui.Widget;

public class SessionNotFoundListenerCollection extends
		ArrayList<SessionNotFoundListener> {

	/**
	 * UID.
	 */
	private static final long serialVersionUID = 6036561316166151451L;

	public void fireSessionNotFoundListener(Widget sender) {
		for (SessionNotFoundListener listener : this) {
			listener.onSessionNotFound(sender);
		}
	}
}
