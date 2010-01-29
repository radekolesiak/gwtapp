package org.gwtapp.startapp.client;

import org.gwtapp.core.client.html.io.HRpcRequestBuilder;
import org.gwtapp.core.client.html.io.HSubmitCompleteHandler;
import org.gwtapp.core.client.template.HttpTemplateRepository;
import org.gwtapp.core.client.template.HttpTemplater;
import org.gwtapp.startapp.client.api.DownloadService;
import org.gwtapp.startapp.client.api.DownloadServiceAsync;
import org.gwtapp.startapp.client.api.GwtAppService;
import org.gwtapp.startapp.client.api.GwtAppServiceAsync;
import org.gwtapp.startapp.client.data.user.register.UserRegisterModel;
import org.gwtapp.startapp.client.data.user.register.UserRegisterModelImpl;
import org.gwtapp.startapp.client.ui.ExternalTemplatingPanel;
import org.gwtapp.startapp.client.ui.InternalTemplatingPanel;
import org.gwtapp.startapp.client.ui.UploadPanel;
import org.gwtapp.startapp.client.ui.UserRegisterTab;
import org.gwtapp.startapp.client.ui.UserRegisterTabExt;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.ServiceDefTarget;
import com.google.gwt.user.client.ui.Button;

public class StartApp implements EntryPoint {

	public final static GwtAppServiceAsync service = GWT
			.create(GwtAppService.class);

	public final static DownloadServiceAsync downloader = GWT
			.create(DownloadService.class);
	static {
		HRpcRequestBuilder.updateService((ServiceDefTarget) downloader);
	}

	/* Two similar ways of templating. */
	public final static HttpTemplater templater = new HttpTemplater(
			"/templates/");
	public final static HttpTemplateRepository templateService = new HttpTemplateRepository(
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

	@Override
	public void onModuleLoad() {
		Timer timer = new Timer() {
			@Override
			public void run() {
				ui();
			}
		};
		timer.schedule(1000);
	}

	private void ui() {
		new UserRegisterTabExt();
		final UserRegisterTab urt = new UserRegisterTab();
		urt.getTabPanel().add(clear);
		urt.getTabPanel().add(download);
		urt.getTabPanel().add(upload);
		urt.getTabPanel().add(new ExternalTemplatingPanel());
		urt.getTabPanel().add(new InternalTemplatingPanel());
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
}
