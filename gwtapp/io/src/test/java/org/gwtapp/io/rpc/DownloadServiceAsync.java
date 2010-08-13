package org.gwtapp.io.rpc;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface DownloadServiceAsync {
	void dowloadText(String text, AsyncCallback<Void> callback);
}
