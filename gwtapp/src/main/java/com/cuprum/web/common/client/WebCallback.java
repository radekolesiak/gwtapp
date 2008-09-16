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
		IFailureCallbackHandler handler = getIFailureCallbackHandler();
		if (handler == null) {
			Util.showOnFailureCallbackDefault();
		} else {
			handler.onResponseFailure(caught);
		}
	}

	public void onAfter() {
	}

	private static IFailureCallbackHandler handler = null;

	public static void setIFailureCallbackHandler(
			final IFailureCallbackHandler handler) {
		WebCallback.handler = handler;
	}

	public static IFailureCallbackHandler getIFailureCallbackHandler() {
		return handler;
	}
}
