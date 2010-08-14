package org.gwtapp.core.client;

import org.gwtapp.core.rpc.exception.RpcException;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.StatusCodeException;

public class AsyncCallbackAdapter<T> implements AsyncCallback<T> {

	public static final String SERVER_ERROR = "There was a problem accessing your website. Check that you are connected to the Internet.";

	public AsyncCallbackAdapter() {
	}

	private static void message(Throwable e, String message) {
		Window.alert(e + " " + message);
	}

	protected void onRpcError(RpcException e) {
		message(e, SERVER_ERROR);
	}

	public static void onInternalError(Throwable e) {
		message(e, SERVER_ERROR);
	}

	public void onCommonBefore() {
	}

	public void onCommonAfter() {
	}

	private boolean isIgnored(Throwable e) {
		if (e instanceof StatusCodeException) {
			StatusCodeException statusCodeException = (StatusCodeException) e;
			if (statusCodeException.getStatusCode() == 0) {
				return true;
			}
		} else if (e instanceof RuntimeException) {
			String msg = e.getMessage();
			if (msg != null
					&& msg.toLowerCase().contains("xmlhttprequest.status")) {
				return true;
			}
		}
		return false;
	}

	@Override
	public final void onFailure(Throwable caught) {
		try {
			onCommonBefore();
			onFailureCallback(caught);
			onCommonAfter();
			throw caught;
		} catch (Throwable e) {
			try {
				try {
					throw e;
				} catch (RpcException f) {
					onRpcError(f);
				}
			} catch (Throwable f) {
				if (!isIgnored(f)) {
					onInternalError(f);
				}
			}
		}
	}

	@Override
	public final void onSuccess(T result) {
		try {
			onCommonBefore();
			onSuccessCallback(result);
			onCommonAfter();
		} catch (Throwable e) {
			if (!isIgnored(e)) {
				onInternalError(e);
			}
		}
	};

	public void onFailureCallback(Throwable caught) throws Throwable {
		throw caught;
	}

	public void onSuccessCallback(T result) {
	}
}