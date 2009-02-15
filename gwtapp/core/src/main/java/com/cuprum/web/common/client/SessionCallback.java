package com.cuprum.web.common.client;

import com.cuprum.web.common.client.exceptions.usersession.SessionNotFoundException;

public abstract class SessionCallback<T> extends WebCallback<T> {

	public final void onResponseSuccess(final T result) {
		onSessionResponseSuccess(result);
	}

	@Override
	public void onResponseFailure(final Throwable caught) {
		if (caught instanceof SessionNotFoundException) {
			onSessionNotFound();
		} else {
			super.onResponseFailure(caught);
		}
	}

	public abstract void onSessionResponseSuccess(final T result);

	public abstract void onSessionNotFound();
}
