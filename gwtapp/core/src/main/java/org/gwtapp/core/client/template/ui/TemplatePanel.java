package org.gwtapp.core.client.template.ui;

import java.util.HashMap;
import java.util.Map;

import org.gwtapp.core.client.template.TemplateRepositoryHandler;
import org.gwtapp.core.client.template.TemplateUtils;
import org.gwtapp.core.client.template.WidgetHandler;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Element;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.Widget;

public class TemplatePanel<T> extends HTMLPanel implements
		TemplateRepositoryHandler, HasValue<T> {

	private final Map<String, WidgetHandler> widgetHandlers = new HashMap<String, WidgetHandler>();

	private boolean isTemplated = false;
	private String template = "";

	private T value;

	public TemplatePanel() {
		super("");
	}

	public void addWidgetHandler(String name, WidgetHandler handler) {
		widgetHandlers.put(name, handler);
	}

	public void removeWidgetHandler(String name) {
		widgetHandlers.remove(name);
	}

	@Override
	protected void onAttach() {
		super.onAttach();
		if (isTemplated) {
			addWidgets();
		}
	}

	private void addWidgets() {
		final Map<String, String> ids = new HashMap<String, String>();
		for (String template : widgetHandlers.keySet()) {
			ids.put(template, HTMLPanel.createUniqueId());
		}
		DOM.setInnerHTML(getElement(), TemplateUtils.replaceTemplate(template,
				ids));
		for (Map.Entry<String, String> entry : ids.entrySet()) {
			String field = entry.getKey();
			String id = entry.getValue();
			Element element = DOM.getElementById(id);
			if (element != null) {
				String style = element.getAttribute("class");
				Widget widget = widgetHandlers.get(field).onWidget(id);
				if (style != null && !style.isEmpty()) {
					widget.addStyleName(style);
				}
				addAndReplaceElement(widget, id);
			}
		}
	}

	@Override
	public void onFailure(Throwable e) {
		GWT.getUncaughtExceptionHandler().onUncaughtException(e);
	}

	@Override
	public void onTemplate(String template) {
		if (!isTemplated) {
			this.template = template;
			isTemplated = true;
			if (isAttached()) {
				addWidgets();
			}
		}
	}

	@Override
	public T getValue() {
		return value;
	}

	@Override
	public void setValue(T value) {
		setValue(value, false);
	}

	@Override
	public void setValue(T value, boolean fireEvents) {
		this.value = value;
		if (fireEvents) {
			ValueChangeEvent.fire(this, value);
		}
	}

	@Override
	public HandlerRegistration addValueChangeHandler(
			ValueChangeHandler<T> handler) {
		return addHandler(handler, ValueChangeEvent.getType());
	};

}