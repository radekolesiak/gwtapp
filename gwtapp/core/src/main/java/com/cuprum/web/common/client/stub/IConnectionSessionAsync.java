package com.cuprum.web.common.client.stub;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface IConnectionSessionAsync {
	void getNewConnectionSession(AsyncCallback<Object> callback);
}
