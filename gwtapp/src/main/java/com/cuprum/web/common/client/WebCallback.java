package com.cuprum.web.common.client;

import com.google.gwt.user.client.rpc.AsyncCallback;

public abstract class WebCallback<T> implements AsyncCallback<T> {
	public final void onFailure(final Throwable caught) {
		onBefore();
		onResponseFailure(caught);
		onAfter();
	}

	public final void onSuccess(final T result) {
		onBefore();
		onResponseSuccess(result);
		onAfter();
	}

	public void onBefore() {
	}

	public abstract void onResponseSuccess(final T result);

	public void onResponseFailure(Throwable caught) {
		Util.showOnFailureCallbackDefault();
	}

	public void onAfter() {
	}
}
