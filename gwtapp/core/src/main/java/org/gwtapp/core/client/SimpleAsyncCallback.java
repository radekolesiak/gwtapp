package org.gwtapp.core.client;

import com.google.gwt.user.client.rpc.AsyncCallback;

public class SimpleAsyncCallback<T> implements AsyncCallback<T> {
	
	@Override
	public void onFailure(Throwable e) {
	}

	@Override
	public void onSuccess(T result) {
	}
}
