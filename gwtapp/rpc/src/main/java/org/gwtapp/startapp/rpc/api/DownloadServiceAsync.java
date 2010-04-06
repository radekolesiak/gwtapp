package org.gwtapp.startapp.rpc.api;

import org.gwtapp.startapp.rpc.data.user.register.UserRegisterModel;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface DownloadServiceAsync {
	void download(UserRegisterModel userRegister, AsyncCallback<Void> callback);
}
