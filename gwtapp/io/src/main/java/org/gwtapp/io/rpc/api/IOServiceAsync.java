package org.gwtapp.io.rpc.api;

import java.io.Serializable;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.IsSerializable;

public interface IOServiceAsync {
	void getResponseIsSerializable(AsyncCallback<IsSerializable> callback);
	void getResponseSerializable(AsyncCallback<Serializable> callback);
}
