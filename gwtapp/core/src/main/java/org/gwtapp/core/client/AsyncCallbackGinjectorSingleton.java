package org.gwtapp.core.client;

import com.google.gwt.user.client.rpc.AsyncCallback;

public class AsyncCallbackGinjectorSingleton {

	private static AsyncCallbackGinjector injector;

	public static <T> AsyncCallback<T> create(final AsyncCallback<T> callback) {
		return getInjector().get().create(callback);
	}

	public static void setInjector(AsyncCallbackGinjector injector) {
		AsyncCallbackGinjectorSingleton.injector = injector;
	}

	public static AsyncCallbackGinjector getInjector() {
		return injector;
	}
}
