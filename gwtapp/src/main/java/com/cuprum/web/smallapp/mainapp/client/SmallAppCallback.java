package com.cuprum.web.smallapp.mainapp.client;

import com.cuprum.web.common.client.SessionCallback;

public abstract class SmallAppCallback<T> extends SessionCallback<T> {
	/** Default action on session lost. */
	@Override
	public void onSessionNotFound() {
		defaultOnSessionNotFound();
	}

	@Override
	public void onSessionResponseFailure(Throwable caught) {
	}
	
	public static void defaultOnSessionNotFound() {
		//MainApp.showWelcome();
	}
}
