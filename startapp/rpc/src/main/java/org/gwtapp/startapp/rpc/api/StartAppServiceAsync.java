package org.gwtapp.startapp.rpc.api;

import org.gwtapp.startapp.rpc.data.user.register.UserRegisterModel;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface StartAppServiceAsync {
	void register(UserRegisterModel user, AsyncCallback<Void> callback);
	void feedback(String from, String feedback, AsyncCallback<Void> callback); 
}
