package com.cuprum.web.widgets.common.client;

import com.cuprum.web.common.client.i18n.InfoMessages;
import com.extjs.gxt.ui.client.widget.Info;
import com.google.gwt.core.client.GWT;

public class Alerts {
	public void showOnFailureCallbackDefault(Throwable e) {
		InfoMessages messages = GWT.create(InfoMessages.class);
		Info.display(messages.msgOnFailureTitle(), messages.msgOnFailure(),
				new String[] {});
	}
}
