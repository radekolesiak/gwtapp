package org.gwtapp.core.client.html.core.api;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.IsSerializable;

public interface HTMLServiceAsync {
	void getResponse(AsyncCallback<IsSerializable> callback);
}
