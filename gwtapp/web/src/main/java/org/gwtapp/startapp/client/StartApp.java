package org.gwtapp.startapp.client;

import org.gwtapp.core.client.html.io.HRpcRequestBuilder;
import org.gwtapp.core.client.html.io.HSubmitCompleteHandler;
import org.gwtapp.core.client.template.HttpTemplateRepository;
import org.gwtapp.core.client.template.Template;
import org.gwtapp.core.client.template.TemplateRepositoryHandlerAdapter;
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

	public static Template getTemplateFromSynchronizedRepositoryMockup() {
		// This can be e.g. Dictionary
		return new Template(
				"p",
				"",
				"<div style=\"background-color:#efcfcf;\">@This is HTML template mockup - begin<br /><ul>"
						+ "<li>UI Panel 1 - <a href=\"http://gwtapp.svn.sourceforge.net/viewvc/gwtapp/trunk/gwtapp/web/src/main/java/org/gwtapp/startapp/client/StartApp.java?view=markup\">StartApp.java</a></li>"
						+ "<li>UI Panel 2 - <a href=\"http://gwtapp.svn.sourceforge.net/viewvc/gwtapp/trunk/gwtapp/web/src/main/java/org/gwtapp/startapp/client/ui/ExternalTemplatingPanel.java?view=markup\">ExternalTemplatingPanel.java</a></li>"
						+ "</ul><div><input template=\"login\" type=\"text\" style=\"width:250px;\" class=\"style-t1\" value=\"it should be replaced to the empty field\"/></div><div>BBB<div>CCC<input template=\"password\" type=\"text\" style=\"width:250px;\" class=\"style-t2\" value=\"it should be replaced to the empty field\"/></div></div>@This is HTML template mockup - end</div>");
	}

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
		if (Window.Location.getHostName().equals("localhost")) {
			timer.schedule(10);
		} else {
			timer.schedule(1000);
		}
	}

	private void ui() {
		new UserRegisterTabExt();
		final UserRegisterTab urt = new UserRegisterTab();
		urt.getTabPanel().add(clear);
		urt.getTabPanel().add(download);
		urt.getTabPanel().add(upload);
		
		// template 1
		urt.getTabPanel().add(new InternalTemplatingPanel());
		
		// template 2
		urt.getTabPanel().add(
				new ExternalTemplatingPanel(
						getTemplateFromSynchronizedRepositoryMockup()));
		
		// template 3
		templateService.load("startapp.jsp?type=external",
				new TemplateRepositoryHandlerAdapter() {
					@Override
					public void onTemplate(Template template) {
						urt.getTabPanel().add(
								new ExternalTemplatingPanel(template));
					}
				});

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
