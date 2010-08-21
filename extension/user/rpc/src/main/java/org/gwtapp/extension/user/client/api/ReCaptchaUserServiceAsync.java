package org.gwtapp.extension.user.client.api;

import org.gwtapp.extension.user.client.data.ReCaptchaUserImpl;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface ReCaptchaUserServiceAsync {
	void addUser(ReCaptchaUserImpl user, AsyncCallback<Long> callback);
}
