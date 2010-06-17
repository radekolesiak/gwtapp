package org.gwtapp.startapp.client;

import org.gwtapp.html.rpc.HtmlRpcService;
import org.gwtapp.io.client.IORpcRequestBuilder;
import org.gwtapp.startapp.rpc.api.DownloadService;
import org.gwtapp.startapp.rpc.api.DownloadServiceAsync;
import org.gwtapp.startapp.rpc.api.GwtAppService;
import org.gwtapp.startapp.rpc.api.GwtAppServiceAsync;
import org.gwtapp.template.client.DOMTemplateRepository;
import org.gwtapp.template.client.TemplateRepository;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.ServiceDefTarget;

public class StartAppEntryPoint implements EntryPoint {

	public final static GwtAppServiceAsync service = GWT
			.create(GwtAppService.class);

	public final static TemplateRepository templates = new TemplateRepository(
			"/templates/");
	public final static DOMTemplateRepository domTemplates = new DOMTemplateRepository();

	public final static DownloadServiceAsync downloader = GWT
			.create(DownloadService.class);
	static {
		IORpcRequestBuilder.updateService((ServiceDefTarget) downloader);
	}
	
	public final static HtmlRpcService htmlRpc = new HtmlRpcService();

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
