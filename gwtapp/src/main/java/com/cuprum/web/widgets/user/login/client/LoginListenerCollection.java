package com.cuprum.web.widgets.user.login.client;

import java.util.ArrayList;

import com.cuprum.web.common.client.data.TUserSession;
import com.google.gwt.user.client.ui.Widget;

public class LoginListenerCollection extends ArrayList<LoginListener> {

	/**
	 * UID.
	 */
	private static final long serialVersionUID = 3666565996100560518L;

	public void fireLoginListener(Widget sender, TUserSession session) {
		for (LoginListener listener : this) {
			listener.onLogin(sender, session);
		}
	}
}
