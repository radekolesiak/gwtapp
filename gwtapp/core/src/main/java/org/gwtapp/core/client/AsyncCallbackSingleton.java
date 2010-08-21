package org.gwtapp.core.client;

import com.google.gwt.user.client.rpc.AsyncCallback;

public class AsyncCallbackSingleton {

	private static AsyncCallbackInjector injector;

	public static <T> AsyncCallback<T> create(final AsyncCallback<T> callback) {
		return getInjector().create(callback);
	}

	public static void setInjector(AsyncCallbackInjector injector) {
		AsyncCallbackSingleton.injector = injector;
	}

	public static AsyncCallbackInjector getInjector() {
		return injector;
	}
}
