package org.gwtapp.startapp.client;

import org.gwtapp.core.client.AsyncCallbackInjector;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.inject.Inject;

public class AsyncCallbackGinjector {	
	
	private final AsyncCallbackInjector injector;

	@Inject
	public AsyncCallbackGinjector(AsyncCallbackInjector injector) {
		this.injector = injector;
	}

	public <T> AsyncCallback<T> create(final AsyncCallback<T> callback) {
		return injector.create(callback);
	}
}
