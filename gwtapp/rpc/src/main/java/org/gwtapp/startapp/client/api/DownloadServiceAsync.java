package org.gwtapp.startapp.client.api;

import org.gwtapp.startapp.client.data.user.register.UserRegister;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface DownloadServiceAsync {
	void download(UserRegister userRegister, AsyncCallback<Void> callback);
}
