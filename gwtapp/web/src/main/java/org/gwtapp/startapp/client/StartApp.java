package org.gwtapp.startapp.client;

import org.gwtapp.startapp.client.api.GwtAppService;
import org.gwtapp.startapp.client.api.GwtAppServiceAsync;
import org.gwtapp.startapp.client.ui.UserRegisterTabExt;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;

public class StartApp implements EntryPoint {

	// GAE + GWT:
	// http://code.google.com/intl/pl/appengine/docs/java/tools/eclipse.html

	public final static GwtAppServiceAsync service = GWT
			.create(GwtAppService.class);

	public final static AsyncCallback<Void> callback = new AsyncCallback<Void>() {
		@Override
		public void onFailure(Throwable caught) {
			caught.printStackTrace();
		}

		@Override
		public void onSuccess(Void result) {
		}
	};

	@Override
	public void onModuleLoad() {
		new UserRegisterTabExt();
	}

}
