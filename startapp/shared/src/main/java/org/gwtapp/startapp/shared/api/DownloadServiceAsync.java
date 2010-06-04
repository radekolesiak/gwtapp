package org.gwtapp.startapp.shared.api;

import org.gwtapp.startapp.shared.data.user.register.UserRegisterModel;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface DownloadServiceAsync {
	void download(UserRegisterModel userRegister, AsyncCallback<Void> callback);
}
