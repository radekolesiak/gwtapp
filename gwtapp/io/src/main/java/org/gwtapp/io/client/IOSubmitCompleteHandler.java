package org.gwtapp.io.client;

import org.gwtapp.io.rpc.IOClient;

import com.google.gwt.user.client.ui.FormPanel.SubmitCompleteEvent;
import com.google.gwt.user.client.ui.FormPanel.SubmitCompleteHandler;

public abstract class IOSubmitCompleteHandler<T> implements
		SubmitCompleteHandler {

	@SuppressWarnings("unchecked")
	@Override
	public final void onSubmitComplete(SubmitCompleteEvent event) {
		try {
			Object result = IOClient.getSerializedObject(IOClient.decode(event
					.getResults()));
			if (result instanceof Throwable) {
				onFailure((Throwable) result);
			} else {
				onSuccessful((T) result);
			}
		} catch (Throwable e) {
			onFailure(e);
		}
	}

	public abstract void onSuccessful(T result);

	public abstract void onFailure(Throwable e);
}
