package com.cuprum.web.smallapp.mainapp.client;

import com.cuprum.web.common.client.SessionCallback;
import com.cuprum.web.common.client.Util;
import com.cuprum.web.smallapp.mainapp.client.i18n.InfoMessages;
import com.extjs.gxt.ui.client.widget.Info;
import com.google.gwt.core.client.GWT;

public abstract class SmallAppCallback<T> extends SessionCallback<T> {
	/** Default action on session lost. */
	@Override
	public void onSessionNotFound() {
		defaultOnSessionNotFound();
	}

	@Override
	public void onSessionResponseFailure(Throwable caught) {
		Util.showOnFailureCallbackDefault();
	}

	public static void defaultOnSessionNotFound() {
		InfoMessages messages = GWT.create(InfoMessages.class);
		Info.display(messages.msgSessionNotFoundTitle(), messages
				.msgSessionNotFound(), new String[] {});
	}
}
