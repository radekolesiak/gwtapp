package com.cuprum.web.common.client;

import com.google.gwt.user.client.rpc.AsyncCallback;

public abstract class WebCallback<T> implements AsyncCallback<T> {
	public final void onFailure(final Throwable caught) {
		onBefore();
		try {
			onResponseFailure(caught);
		} finally {
			onAfter();
		}
	}

	public final void onSuccess(final T result) {
		onBefore();
		try {
			onResponseSuccess(result);
		} finally {
			onAfter();
		}
	}

	public void onBefore() {
	}

	public void onAfter() {
	}

	public abstract void onResponseSuccess(final T result);

	public void onResponseFailure(Throwable caught) {
		if (getIFailureCallbackHandler() != null) {
			getIFailureCallbackHandler().onResponseFailure(caught);
		}
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
