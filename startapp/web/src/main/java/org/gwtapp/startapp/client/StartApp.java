package org.gwtapp.startapp.client;

import org.gwtapp.startapp.client.ui.feedback.FeedbackPanel;
import org.gwtapp.startapp.client.ui.user.register.UploadDownloadTemplatePanel;
import org.gwtapp.startapp.client.ui.user.register.UserRegisterTemplatePanel;
import org.gwtapp.startapp.rpc.data.user.register.UserRegisterModel;

import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.DeferredCommand;
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
		String rpcName = "userregister";
		UserRegisterModel value = (UserRegisterModel) rpc.getValue(rpcName);
		new UploadDownloadTemplatePanel(value, "ud").attach();
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
