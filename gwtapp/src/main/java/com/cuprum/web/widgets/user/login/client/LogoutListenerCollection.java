package com.cuprum.web.widgets.user.login.client;

import java.util.ArrayList;

import com.google.gwt.user.client.ui.Widget;

public class LogoutListenerCollection extends ArrayList<LogoutListener> {

	/**
	 * UID.
	 */
	private static final long serialVersionUID = 8758862044906704140L;

	public void fireLogoutListener(Widget sender) {
		for (LogoutListener listener : this) {
			listener.onLogout(sender);
		}
	}

	public void fireSessionNotFound(Widget sender) {
		for (LogoutListener listener : this) {
			listener.onSessionNotFound(sender);
		}
	}
}
