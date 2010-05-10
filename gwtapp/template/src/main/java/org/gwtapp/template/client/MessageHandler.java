package org.gwtapp.template.client;

import com.google.gwt.user.client.ui.HasHTML;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.Widget;

public class MessageHandler<T extends Widget> implements WidgetHandler {

	private TemplateMessage templateMessage = null;
	private T widget = null;
	private String id = null;

	public MessageHandler() {
		this(null);
	}

	public MessageHandler(T widget) {
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

	public T getWidget() {
		return widget;
	}

	public TemplateMessage getTemplateMessage() {
		return templateMessage;
	}

	public String getMessage(String name, String... params) {
		return getTemplateMessage().getMessage(name, params);
	}

	public void updateWidgetMessage(String name, String... params) {
		T widget = getWidget();
		if (widget instanceof HasHTML || widget instanceof HasText) {
			String msg = getMessage(name, params);
			if (widget instanceof HasHTML) {
				((HasHTML) widget).setHTML(msg);
			} else if (widget instanceof HasText) {
				((HasText) widget).setText(msg);
			}
		}
	}

	public boolean hasWidget() {
		return widget != null;
	}
}
