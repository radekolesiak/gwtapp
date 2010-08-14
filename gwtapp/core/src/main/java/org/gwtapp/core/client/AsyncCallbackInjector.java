package org.gwtapp.core.client;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface AsyncCallbackInjector {
	<T> AsyncCallback<T> create(final AsyncCallback<T> callback);
}
