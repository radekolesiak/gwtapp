package org.gwtapp.core.rpc.api;

import java.io.Serializable;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.IsSerializable;

public interface CoreServiceAsync {
	void getResponseIsSerializable(AsyncCallback<IsSerializable> callback);
	void getResponseSerializable(AsyncCallback<Serializable> callback);
}
