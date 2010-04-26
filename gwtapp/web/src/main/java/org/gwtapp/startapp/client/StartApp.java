package org.gwtapp.startapp.client;

import org.gwtapp.core.client.html.io.HRpcRequestBuilder;
import org.gwtapp.core.client.html.io.HSubmitCompleteHandler;
import org.gwtapp.startapp.client.ui.SyncRepositoryTemplatingPanel;
import org.gwtapp.startapp.client.ui.UploadPanel;
import org.gwtapp.startapp.client.ui.UserRegisterTab;
import org.gwtapp.startapp.client.ui.UserRegisterTabExt;
import org.gwtapp.startapp.rpc.api.DownloadService;
import org.gwtapp.startapp.rpc.api.DownloadServiceAsync;
import org.gwtapp.startapp.rpc.api.GwtAppService;
import org.gwtapp.startapp.rpc.api.GwtAppServiceAsync;
import org.gwtapp.startapp.rpc.data.user.register.UserRegisterModel;
import org.gwtapp.startapp.rpc.data.user.register.UserRegisterModelImpl;
import org.gwtapp.template.client.HttpTemplateRepository;
import org.gwtapp.template.client.SyncTemplateRepository;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.ServiceDefTarget;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.RootPanel;

public class StartApp implements EntryPoint {

	public final static GwtAppServiceAsync service = GWT
			.create(GwtAppService.class);

	public final static DownloadServiceAsync downloader = GWT
			.create(DownloadService.class);
	static {
		HRpcRequestBuilder.updateService((ServiceDefTarget) downloader);
	}

	public final static HttpTemplateRepository templateService = new HttpTemplateRepository(
			"/templates/");
	public final static SyncTemplateRepository syncTemplateService = new SyncTemplateRepository(
			"/templates/");

	public final static AsyncCallback<Void> callback = new AsyncCallback<Void>() {
		@Override
		public void onFailure(Throwable caught) {
			caught.printStackTrace();
		}

		@Override
		public void onSuccess(Void result) {
		}
	};

	private final Button clear = new Button("Clear");
	private final Button download = new Button("Download");
	private final UploadPanel upload = new UploadPanel();

	private final static String TEMPLATES_DIV = "templates";

	@Override
	public void onModuleLoad() {
		hwidgets();
		template();
	}

	private void hwidgets() {
		new UserRegisterTabExt();
		final UserRegisterTab urt = new UserRegisterTab();
		urt.getTabPanel().add(clear);
		urt.getTabPanel().add(download);
		urt.getTabPanel().add(upload);

		clear.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				urt.getTabPanel().setUserRegisterModel(
						new UserRegisterModelImpl());
			}
		});
		download.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				downloader.download(urt.getTabPanel().getUserRegisterModel(),
						callback);
			}
		});
		upload
				.addSubmitCompleteHandler(new HSubmitCompleteHandler<UserRegisterModel>() {
					@Override
					public void onFailure(Throwable e) {
						Window.alert("There was a problem while uploading");
					}

					@Override
					public void onSuccessful(UserRegisterModel result) {
						urt.getTabPanel().setUserRegisterModel(result);
					}
				});
	}

	private void template() {
		RootPanel.get(TEMPLATES_DIV).add(new SyncRepositoryTemplatingPanel());
	}
}
