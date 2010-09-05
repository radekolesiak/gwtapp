package org.gwtapp.core.client.di;

import com.google.gwt.user.client.rpc.AsyncCallback;

public class AsyncCallbackAdapter<T> implements AsyncCallback<T> {

	private GwtTestAsyncCallbackInjector owner;
	private boolean expectedTest = false;
	private Class<?> excpectedException = null;

	@Override
	public final void onSuccess(T result) {
		onSuccessCallback(result);
	}

	@Override
	public final void onFailure(Throwable e) {
		if (isExpectedTest()) {
			try {
				onFailureCallback(e);
			} catch (Throwable f) {
				GwtTestAsyncCallbackInjector.assertEquals(excpectedException,
						f.getClass());
				owner.doFinishTest();
			}
		} else {
			onFailureCallback(e);
		}
	}

	public void onSuccessCallback(T result) {
	}

	public void onFailureCallback(Throwable e) {
	}

	public void setExpectedTest(boolean expectedTest) {
		this.expectedTest = expectedTest;
	}

	public boolean isExpectedTest() {
		return expectedTest;
	}

	public void setExcpectedException(Class<?> excpectedException) {
		this.excpectedException = excpectedException;
	}

	public Class<?> getExcpectedException() {
		return excpectedException;
	}

	public void setOwner(GwtTestAsyncCallbackInjector owner) {
		this.owner = owner;
	}

	public GwtTestAsyncCallbackInjector getOwner() {
		return owner;
	}
}
