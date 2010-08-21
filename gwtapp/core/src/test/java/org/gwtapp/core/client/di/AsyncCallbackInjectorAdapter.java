package org.gwtapp.core.client.di;

import org.gwtapp.core.client.AsyncCallbackInjector;

import com.google.gwt.user.client.rpc.AsyncCallback;

public class AsyncCallbackInjectorAdapter implements AsyncCallbackInjector {
	@Override
	public <T> AsyncCallback<T> create(final AsyncCallback<T> callback) {
		return new AsyncCallbackAdapter<T>() {
			@Override
			public void onSuccessCallback(T result) {
				callback.onSuccess(result);
			};

			@Override
			public void onFailureCallback(Throwable caught) {
				callback.onFailure(caught);
			}
		};
	}
}
