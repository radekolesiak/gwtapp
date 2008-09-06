package com.cuprum.web.common.client;

import com.cuprum.web.common.client.exceptions.model.usersession.SessionNotFoundException;

public abstract class SessionCallback<T> extends WebCallback<T> {
	public final void onResponseFailure(final Throwable caught) {
		try {
			throw caught;
		} catch (SessionNotFoundException e) {
			onSessionNotFound();
		} catch (Throwable e) {
			try {
				onSessionResponseFailure(e);
			} catch (Throwable g) {
			}
		}
	}

	public final void onResponseSuccess(final T result) {
		onSessionResponseSuccess(result);
	}

	public abstract void onSessionResponseSuccess(final T result);

	public void onSessionResponseFailure(final Throwable caught) {
		super.onResponseFailure(caught);
	}

	public abstract void onSessionNotFound();
}
