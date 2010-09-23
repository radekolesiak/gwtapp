package org.gwtapp.validation.client;

import org.gwtapp.validation.rpc.exception.ValidationException;

import com.google.gwt.user.client.rpc.AsyncCallback;

public class ValidationAsyncCallback<T, V extends ValidationException>
		implements AsyncCallback<T> {

	@SuppressWarnings("unchecked")
	@Override
	public final void onFailure(Throwable e) {
		if (e instanceof ValidationException) {
			V validation = null;
			try {
				validation = (V) e;
				if (validation != null) {
					onCallbackValidation(validation);
				}
			} catch (Exception v) {
				onCallbackFailure(v);
			}
		} else {
			onCallbackFailure(e);
		}
	}

	@Override
	public final void onSuccess(T result) {
		onCallbackSuccess(result);
	}

	public void onCallbackSuccess(T result) {

	}

	public void onCallbackValidation(V validation) {
	}

	public void onCallbackFailure(Throwable e) {

	}
}
