package org.gwtapp.startapp.client.ui;

import org.gwtapp.startapp.client.StartAppEntryPoint;
import org.gwtapp.template.client.handlers.TextAreaHandler;
import org.gwtapp.template.client.handlers.TextBoxHandler;
import org.gwtapp.template.client.handlers.WidgetHandler;
import org.gwtapp.template.client.ui.TemplatePanel;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;

public class FeedbackPanel extends TemplatePanel<Void> {

	private final TextBoxHandler email = new TextBoxHandler();
	private final TextAreaHandler message = new TextAreaHandler();
	private final WidgetHandler send = new WidgetHandler();
	private final WidgetHandler indicator = new WidgetHandler();
	private final WidgetHandler sent = new WidgetHandler();
	private final WidgetHandler error = new WidgetHandler();

	public FeedbackPanel() {
		super(StartAppEntryPoint.templates.load("feedback.jsp"));
		addWidgetHandler("email", email);
		addWidgetHandler("message", message);
		addWidgetHandler("send", send);
		addWidgetHandler("indicator", indicator);
		addWidgetHandler("sent", sent);
		addWidgetHandler("error", error);
	}

	@Override
	public void onAddWidgets() {
		send.getWidget().addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				sendFeedback();
			}
		});
	}

	private void sendFeedback() {
		indicator.getWidget().setVisible(true);
		send.getWidget().setEnabled(false);
		StartAppEntryPoint.service.feedback(email.getWidget().getValue(),
				message.getWidget().getValue(), new AsyncCallback<Void>() {
					@Override
					public void onSuccess(Void result) {
						indicator.getWidget().setVisible(false);
						send.getWidget().setEnabled(true);
						sent.getWidget().setVisible(true);
						error.getWidget().setVisible(false);
					}

					@Override
					public void onFailure(Throwable e) {
						indicator.getWidget().setVisible(false);
						send.getWidget().setEnabled(true);
						sent.getWidget().setVisible(false);
						error.getWidget().setVisible(true);
					}
				});
	}
}
