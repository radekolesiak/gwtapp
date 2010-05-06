package org.gwtapp.template.client;

import com.google.gwt.user.client.ui.Widget;

public class MessageWidgetHandler<T extends Widget> implements WidgetHandler {

	private TemplateMessage templateMessage = null;
	private T widget = null;
	private String id = null;

	public MessageWidgetHandler() {
		this(null);
	}

	public MessageWidgetHandler(T widget) {
		this.widget = widget;
	}

	@Override
	public final T onWidget(String id) {
		this.id = id;
		setTemplateMessage(new TemplateMessage(id));
		return widget = onWidget();
	}

	protected T onWidget() {
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
