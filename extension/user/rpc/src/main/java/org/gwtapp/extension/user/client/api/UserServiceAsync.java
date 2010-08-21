package org.gwtapp.extension.user.client.api;

import org.gwtapp.extension.user.client.data.User;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface UserServiceAsync {
	void getUser(String login, AsyncCallback<User> callback);
}
