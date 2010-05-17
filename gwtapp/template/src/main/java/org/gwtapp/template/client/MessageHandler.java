package org.gwtapp.template.client;

import com.google.gwt.user.client.ui.HasHTML;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.Widget;

public class MessageHandler<T extends Widget> implements TemplateHandler {

	private TemplateMessage templateMessage = null;
	private T defaultWidget = null;
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
		if (widget != null) {
			return widget;
		} else {
			return widget = getDefaultWidget();
		}
	}

	public TemplateMessage getTemplateMessage() {
		return templateMessage;
	}

	public String getMessage(String name, String... params) {
		return getTemplateMessage().getMessage(name, params);
	}

	public String getParamMessage(String name, Param... params) {
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

	public void updateWidgetParamMessage(String name, Param... params) {
		T widget = getWidget();
		if (widget instanceof HasHTML || widget instanceof HasText) {
			String msg = getParamMessage(name, params);
			if (widget instanceof HasHTML) {
				((HasHTML) widget).setHTML(msg);
			} else if (widget instanceof HasText) {
				((HasText) widget).setText(msg);
			}
		}
	}

	protected T getDefaultWidget() {
		return defaultWidget;
	}

	public boolean hasWidget() {
		return getWidget() != null;
	}
}
