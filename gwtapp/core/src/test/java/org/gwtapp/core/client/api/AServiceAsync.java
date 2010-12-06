package org.gwtapp.core.client.api;

import org.gwtapp.core.client.data.B;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface AServiceAsync {
	
	void getB(AsyncCallback<B> callback);

	void setB(B b, AsyncCallback<Boolean> callback);
}
