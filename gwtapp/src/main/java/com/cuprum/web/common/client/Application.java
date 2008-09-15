package com.cuprum.web.common.client;

import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;

public class Application {
	public static void setPage(Widget widget) {
		RootPanel.get("slot").clear();
		RootPanel.get("slot").add(widget);
	}
}
