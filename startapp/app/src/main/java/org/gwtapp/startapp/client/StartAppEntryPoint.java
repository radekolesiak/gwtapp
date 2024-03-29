package org.gwtapp.startapp.client;

import org.gwtapp.io.client.IOHtmlRpcDeserializer;
import org.gwtapp.io.client.IORpcRequestBuilder;
import org.gwtapp.startapp.rpc.api.DownloadService;
import org.gwtapp.startapp.rpc.api.DownloadServiceAsync;
import org.gwtapp.startapp.rpc.api.StartAppService;
import org.gwtapp.startapp.rpc.api.StartAppServiceAsync;
import org.gwtapp.template.client.HtmlRepository;
import org.gwtapp.template.client.TemplateRepository;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.ServiceDefTarget;

public class StartAppEntryPoint implements EntryPoint {

	/** StartApp service **/
	public final static StartAppServiceAsync service = GWT
			.create(StartAppService.class);

	/** Download service **/
	public final static DownloadServiceAsync downloader = GWT
			.create(DownloadService.class);
	static {
		IORpcRequestBuilder.updateService((ServiceDefTarget) downloader);
	}

	/** HTML templates repositories **/
	public final static HtmlRepository html = new HtmlRepository();
	public final static TemplateRepository templates = new TemplateRepository(
			"/templates/");

	/** HTML RPC deserializer */
	public final static IOHtmlRpcDeserializer rpc = new IOHtmlRpcDeserializer();

	/** Void async callback **/
	public final static AsyncCallbackAdapter<Void> callback = new AsyncCallbackAdapter<Void>();

	@Override
	public final void onModuleLoad() {
		onStartAppModuleLoad();
	}

	public void onStartAppModuleLoad() {

	}

}
