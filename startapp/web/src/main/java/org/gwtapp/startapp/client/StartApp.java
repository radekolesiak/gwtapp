package org.gwtapp.startapp.client;

import org.gwtapp.core.client.io.IORpcRequestBuilder;
import org.gwtapp.startapp.client.ui.feedback.FeedbackPanel;
import org.gwtapp.startapp.client.ui.user.register.UserRegisterTemplatePanel;
import org.gwtapp.startapp.rpc.api.DownloadService;
import org.gwtapp.startapp.rpc.api.DownloadServiceAsync;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.ServiceDefTarget;
import com.google.gwt.user.client.ui.RootPanel;

public class StartApp extends StartAppEntryPoint {

	public final static DownloadServiceAsync downloader = GWT
			.create(DownloadService.class);
	static {
		IORpcRequestBuilder.updateService((ServiceDefTarget) downloader);
	}

	private final static String TEMPLATES_DIV = "templates";
	private final static String UPLAOD_DOWNLOAD_DIV = "ud";

	private final UserRegisterTemplatePanel templatePanel = new UserRegisterTemplatePanel();
	private final FeedbackPanel feedback = new FeedbackPanel();

	@Override
	public void onStartAppModuleLoad() {
		try {
			template();
			uploadownload();
			feedback();
		} finally {
			hideLoadingIndicator();
		}
	}

	/*-
	 private final Button clear = new Button("Clear");
	 private final Button download = new Button("Download");
	 private final UploadPanel upload = new UploadPanel();
	 private final TabPanel tabPanel = new TabPanel();
	 private void tabpanel() {
	 tabPanel.add(clear);
	 tabPanel.add(download);
	 tabPanel.add(upload);

	 clear.addClickHandler(new ClickHandler() {
	 @Override
	 public void onClick(ClickEvent event) {
	 tabPanel.setUserRegisterModel(new UserRegisterModelImpl());
	 }
	 });
	 download.addClickHandler(new ClickHandler() {
	 @Override
	 public void onClick(ClickEvent event) {
	 downloader.download(tabPanel.getUserRegisterModel(), callback);
	 }
	 });
	 upload
	 .addSubmitCompleteHandler(new IOSubmitCompleteHandler<UserRegisterModel>() {
	 @Override
	 public void onFailure(Throwable e) {
	 Window.alert("There was a problem while uploading");
	 }

	 @Override
	 public void onSuccessful(UserRegisterModel result) {
	 tabPanel.setUserRegisterModel(result);
	 }
	 });
	 }
	 */
	
	private void template() {
		RootPanel.get(TEMPLATES_DIV).add(templatePanel);
	}

	private void uploadownload() {
		RootPanel.get(UPLAOD_DOWNLOAD_DIV);
	}

	private void feedback() {
		RootPanel anchor = RootPanel.get("feedbackanchor");
		if (anchor != null) {
			anchor.add(feedback);
		}
	}

	private void hideLoadingIndicator() {
		RootPanel indicator = RootPanel.get("laodingindicator");
		if (indicator != null) {
			indicator.setVisible(false);
		}
	}
}
