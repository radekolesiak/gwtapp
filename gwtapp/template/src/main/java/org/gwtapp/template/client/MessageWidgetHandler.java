package org.gwtapp.template.client;

import org.gwtapp.template.client.ui.MessageWidget;

import com.google.gwt.user.client.ui.Widget;

public class MessageWidgetHandler implements WidgetHandler {

	private TemplateMessage templateMessage = null;
	private Widget widget = null;
	private String id = null;

	public MessageWidgetHandler() {
		this(null);
	}

	public MessageWidgetHandler(Widget widget) {
		this.widget = widget;
	}

	@Override
	public final Widget onWidget(String id) {
		if (this.widget == null) {
			this.widget = onCreateDefaultWidget(id);
		}
		this.id = id;
		setTemplateMessage(new TemplateMessage(id));
		return onWidget();
	}

	protected Widget onCreateDefaultWidget(String id) {
		return new MessageWidget(id);
	}

	public Widget onWidget() {
		return widget;
	}

	private void setTemplateMessage(TemplateMessage templateMessage) {
		this.templateMessage = templateMessage;
	}

	public String getId() {
		return id;
	}

	public Widget getWidget() {
		return widget;
	}

	public TemplateMessage getTemplateMessage() {
		return templateMessage;
	}

	public String getMessage(String name, String... params) {
		return getTemplateMessage().getMessage(name, params);
	}
}
