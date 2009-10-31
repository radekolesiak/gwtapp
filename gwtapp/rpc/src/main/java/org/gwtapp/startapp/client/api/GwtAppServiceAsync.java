package org.gwtapp.startapp.client.api;

import org.gwtapp.startapp.client.data.UserRegisterModel;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface GwtAppServiceAsync {
	void register(UserRegisterModel user, AsyncCallback<Void> callback);
}
