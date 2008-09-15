package com.cuprum.web.common.client;

import com.extjs.gxt.ui.client.widget.Viewport;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;

public class Application {
	public static void setPage(Viewport view) {
		setWidget(view);
	}
	
	public static void setWidget(Widget widget) {
		RootPanel.get("slot").clear();
		RootPanel.get("slot").add(widget);
	}
}
