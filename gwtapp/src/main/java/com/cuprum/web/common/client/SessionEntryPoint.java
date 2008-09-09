package com.cuprum.web.common.client;

import com.cuprum.web.common.client.data.TConnectionSession;
import com.cuprum.web.common.client.stub.IConnectionSession;
import com.cuprum.web.common.client.stub.IConnectionSessionAsync;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * Entry point classes define <code>onModuleLoad()</code>. GWT modules
 * tutorial: http://developerlife.com/tutorials/?p=229
 * 
 * @author Radek Olesiak
 */

public abstract class SessionEntryPoint implements EntryPoint {
	public final void onModuleLoad() {
		final AsyncCallback<Object> callback = new AsyncCallback<Object>() {
			public void onFailure(final Throwable caught) {
				onSessionModuleError(caught);
			}

			public void onSuccess(final Object token) {
				TConnectionSession.setSession((TConnectionSession) token);
				onSessionModuleLoad();
			}
		};

		((IConnectionSessionAsync) EndPoint.create(GWT
				.create(IConnectionSession.class))).getNewConnectionSession(
				getModuleName(), callback);
	}

	public abstract void onSessionModuleLoad();

	public abstract void onSessionModuleError(final Throwable caught);

	public abstract String getModuleName();
}
