package org.gwtapp.startapp.client;

import org.gwtapp.startapp.rpc.api.GwtAppService;
import org.gwtapp.startapp.rpc.api.GwtAppServiceAsync;
import org.gwtapp.template.client.DOMTemplateRepository;
import org.gwtapp.template.client.TemplateRepository;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;

public class StartAppEntryPoint implements EntryPoint {

	public final static GwtAppServiceAsync service = GWT
			.create(GwtAppService.class);

	public final static TemplateRepository templates = new TemplateRepository(
			"/templates/");
	public final static DOMTemplateRepository domTemplates = new DOMTemplateRepository();

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
	public final void onModuleLoad() {
		onStartAppModuleLoad();
	}

	public void onStartAppModuleLoad() {

	}

}
