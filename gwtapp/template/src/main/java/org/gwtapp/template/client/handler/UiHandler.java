package org.gwtapp.template.client.handler;

import org.gwtapp.template.client.Param;
import org.gwtapp.template.client.TemplateMessage;

import com.google.gwt.user.client.ui.HasHTML;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.Widget;

public class UiHandler<T extends Widget> implements TemplateHandler {

	private TemplateMessage templateMessage = null;
	private T defaultWidget = null;
	private T widget = null;
	private String id = null;

	public UiHandler() {
		this(null);
	}

	public UiHandler(T widget) {
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
		if (getTemplateMessage() != null) {
			return getTemplateMessage().getMessage(name, params);
		} else {
			return "";
		}
	}

	public String getParamMessage(String name, Param... params) {
		if (getTemplateMessage() != null) {
			return getTemplateMessage().getMessage(name, params);
		} else {
			return "";
		}
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
