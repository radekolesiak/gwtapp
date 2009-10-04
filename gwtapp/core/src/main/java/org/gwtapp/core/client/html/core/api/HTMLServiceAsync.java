package org.gwtapp.core.client.html.core.api;

import org.gwtapp.core.client.html.ui.core.HWidget;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface HTMLServiceAsync {
	void getWidget(String id, AsyncCallback<HWidget> callback);
}
