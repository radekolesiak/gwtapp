package org.gwtapp.startapp.client;

import org.gwtapp.core.client.io.IORpcRequestBuilder;
import org.gwtapp.startapp.client.ui.feedback.FeedbackPanel;
import org.gwtapp.startapp.client.ui.user.register.UploadDownloadTemplatePanel;
import org.gwtapp.startapp.client.ui.user.register.UserRegisterTemplatePanel;
import org.gwtapp.startapp.shared.api.DownloadService;
import org.gwtapp.startapp.shared.api.DownloadServiceAsync;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.DeferredCommand;
import com.google.gwt.user.client.rpc.ServiceDefTarget;
import com.google.gwt.user.client.ui.RootPanel;

public class StartApp extends StartAppEntryPoint {

	public final static String TEMPLATES_DIV = "templates";

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

	private void template() {
		RootPanel.get(TEMPLATES_DIV).add(templatePanel);
	}

	private void uploadownload() {
		// TODO refactor this in smarter way
		String id = "ud";
		UploadDownloadTemplatePanel panel = new UploadDownloadTemplatePanel(id);
		DOM.getElementById(id).setInnerHTML("");
		RootPanel.get(id).add(panel);
	}

	private void feedback() {
		RootPanel anchor = RootPanel.get("feedbackanchor");
		if (anchor != null) {
			anchor.add(feedback);
		}
	}

	private void hideLoadingIndicator() {
		DeferredCommand.addCommand(new Command() {
			@Override
			public void execute() {
				RootPanel indicator = RootPanel.get("laodingindicator");
				if (indicator != null) {
					indicator.setVisible(false);
				}
			}
		});
	}
}
