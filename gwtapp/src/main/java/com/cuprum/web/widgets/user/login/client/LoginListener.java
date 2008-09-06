package com.cuprum.web.widgets.user.login.client;

import com.cuprum.web.common.client.data.TUserSession;
import com.google.gwt.user.client.ui.Widget;

public interface LoginListener {
	void onLogin(Widget sender, TUserSession session);
}
