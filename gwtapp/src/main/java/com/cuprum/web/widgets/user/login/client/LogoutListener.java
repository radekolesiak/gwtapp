package com.cuprum.web.widgets.user.login.client;

import com.cuprum.web.common.client.SessionNotFoundListener;
import com.google.gwt.user.client.ui.Widget;

public interface LogoutListener extends SessionNotFoundListener {
	void onLogout(Widget sender);
}
