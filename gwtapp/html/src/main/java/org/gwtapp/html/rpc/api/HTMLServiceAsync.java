package org.gwtapp.html.rpc.api;

import java.io.Serializable;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.IsSerializable;

public interface HTMLServiceAsync {
	void getResponseIsSerializable(AsyncCallback<IsSerializable> callback);
	void getResponseSerializable(AsyncCallback<Serializable> callback);
}
