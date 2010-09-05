package org.gwtapp.validation.client;

import org.gwtapp.validation.rpc.exception.ValidationException;

import com.google.gwt.user.client.rpc.AsyncCallback;

public class ValidationAsyncCallback<T, V extends ValidationException>
		implements AsyncCallback<T> {

	@SuppressWarnings("unchecked")
	@Override
	public void onFailure(Throwable e) {
		if (e instanceof ValidationException) {
			V validation = null;
			try {
				validation = (V) e;
			} catch (Exception v) {
			}
			if (validation != null) {
				onValidation(validation);
			}
		}
	}

	@Override
	public void onSuccess(T result) {
	}

	public void onValidation(V validation) {
	}
}
