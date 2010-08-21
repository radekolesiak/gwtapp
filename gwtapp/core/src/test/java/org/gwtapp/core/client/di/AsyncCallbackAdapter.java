package org.gwtapp.core.client.di;

import com.google.gwt.user.client.rpc.AsyncCallback;

public class AsyncCallbackAdapter<T> implements AsyncCallback<T> {

	@Override
	public final void onSuccess(T result) {
		onSuccessCallback(result);
	}

	@Override
	public final void onFailure(Throwable e) {
		onFailureCallback(e);
	}

	public void onSuccessCallback(T result) {
	}

	public void onFailureCallback(Throwable e) {
	}
}
