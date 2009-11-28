package org.gwtapp.startapp.client.api;

import org.gwtapp.startapp.client.data.user.register.UserRegisterModel;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface GwtAppServiceAsync {
	void register(UserRegisterModel user, AsyncCallback<Void> callback);
}
