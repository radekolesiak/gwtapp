package org.gwtapp.core.client.html.io;

import org.gwtapp.core.client.html.core.HClient;

import com.google.gwt.user.client.ui.FormPanel.SubmitCompleteEvent;
import com.google.gwt.user.client.ui.FormPanel.SubmitCompleteHandler;

public abstract class HSubmitCompleteHandler<T> implements
		SubmitCompleteHandler {

	@SuppressWarnings("unchecked")
	@Override
	public final void onSubmitComplete(SubmitCompleteEvent event) {
		try {
			Object result = HClient.getSerializedObject(HClient.decode(event
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
