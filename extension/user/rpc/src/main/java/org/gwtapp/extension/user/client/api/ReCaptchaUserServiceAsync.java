package org.gwtapp.extension.user.client.api;

import org.gwtapp.extension.user.client.data.ReCaptchaUser;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface ReCaptchaUserServiceAsync {
	void addUser(ReCaptchaUser user, AsyncCallback<Long> callback);
}
