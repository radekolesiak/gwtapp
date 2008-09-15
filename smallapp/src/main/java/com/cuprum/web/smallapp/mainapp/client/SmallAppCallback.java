package com.cuprum.web.smallapp.mainapp.client;

import com.cuprum.web.common.client.SessionCallback;

public abstract class SmallAppCallback<T> extends SessionCallback<T> {
	/** Default action on session lost. */
	@Override
	public void onSessionNotFound() {
		MainApp.defaultOnSessionNotFound();
	}
	public void onSessionResponseFailure(final Throwable caught) {
		MainApp.defaultOnSessionNotFound();
	}
}
