package org.gwtapp.template.client;

import com.google.gwt.user.client.ui.Widget;

public class MessageWidgetHandler implements WidgetHandler {

	private TemplateMessage templateMessage;
	private final Widget defaultWidget;

	public MessageWidgetHandler() {
		this(null);
	}

	public MessageWidgetHandler(Widget defaultWidget) {
		this.defaultWidget = defaultWidget;
	}

	@Override
	public final Widget onWidget(String id) {
		setTemplateMessage(new TemplateMessage(id));
		return onWidget(id, getTemplateMessage());
	}

	public Widget onWidget(String id, TemplateMessage templateMessage) {
		return defaultWidget;
	}

	private void setTemplateMessage(TemplateMessage templateMessage) {
		this.templateMessage = templateMessage;
	}

	public TemplateMessage getTemplateMessage() {
		return templateMessage;
	}
}
