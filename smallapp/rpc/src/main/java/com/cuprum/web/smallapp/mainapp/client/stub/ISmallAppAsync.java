package com.cuprum.web.smallapp.mainapp.client.stub;

import java.util.Date;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface ISmallAppAsync {
	void getLastSessionActivity(AsyncCallback<Date> callback);

	void forceRemoveSession(AsyncCallback<Object> callback);
}
