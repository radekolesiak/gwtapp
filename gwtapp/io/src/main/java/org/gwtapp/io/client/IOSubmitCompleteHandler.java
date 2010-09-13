package org.gwtapp.io.client;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.FormPanel.SubmitCompleteEvent;
import com.google.gwt.user.client.ui.FormPanel.SubmitCompleteHandler;

public abstract class IOSubmitCompleteHandler<T> implements
		SubmitCompleteHandler, AsyncCallback<T> {
	@SuppressWarnings("unchecked")
	@Override
	public final void onSubmitComplete(SubmitCompleteEvent event) {
		try {
			Object result = IOClient.deserialize(IOClient.decode(event
					.getResults()));
			if (result instanceof Throwable) {
				onFailure((Throwable) result);
			} else {
				onSuccess((T) result);
			}
		} catch (Throwable e) {
			onFailure(e);
		}
	}
}
