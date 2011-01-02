package org.gwtapp.startapp.client.ui.feedback;

import org.gwtapp.startapp.client.AsyncCallbackAdapter;
import org.gwtapp.startapp.client.StartAppEntryPoint;
import org.gwtapp.template.client.handler.TextAreaHandler;
import org.gwtapp.template.client.handler.TextBoxHandler;
import org.gwtapp.template.client.handler.WidgetHandler;
import org.gwtapp.template.client.ui.TemplatePanel;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;

public class FeedbackPanel extends TemplatePanel<Void> {

	private final TextBoxHandler// 
	email = add("email", new TextBoxHandler());

	private final TextAreaHandler//
	message = add("message", new TextAreaHandler());

	private final WidgetHandler//
	send = add("send", new WidgetHandler());

	private final WidgetHandler//
	indicator = add("indicator", new WidgetHandler());

	private final WidgetHandler//
	sent = add("sent", new WidgetHandler());

	private final WidgetHandler//
	error = add("error", new WidgetHandler());

	public FeedbackPanel() {
		super(StartAppEntryPoint.templates.load("feedback.jsp"));
	}

	@Override
	public void onWidgets() {
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
				message.getWidget().getValue(), new AsyncCallbackAdapter<Void>() {
					@Override
					public void onSuccessCallback(Void result) {
						indicator.getWidget().setVisible(false);
						send.getWidget().setEnabled(true);
						sent.getWidget().setVisible(true);
						error.getWidget().setVisible(false);
					}

					@Override
					public void onFailureCallback(Throwable e) {
						indicator.getWidget().setVisible(false);
						send.getWidget().setEnabled(true);
						sent.getWidget().setVisible(false);
						error.getWidget().setVisible(true);
					}
				});
	}
}
