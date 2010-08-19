package org.gwtapp.extension.user.client.api;

import org.gwtapp.extension.user.client.data.User;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface ReCaptchaUserServiceAsync {
	void addUser(User user, AsyncCallback<Long> callback);
}
