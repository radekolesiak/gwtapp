package org.gwtapp.core.client.template.ui;

import java.util.HashMap;
import java.util.Map;

import org.gwtapp.core.client.template.Template;
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
import com.google.gwt.user.client.ui.HasName;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.Widget;

public class TemplatePanel<T> extends HTMLPanel implements
		TemplateRepositoryHandler, HasValue<T>, HasName {

	public static class Style {
		public final static String TEMPLATE_PANEL = "templatePanel";
	}

	private final Map<String, String> ids = new HashMap<String, String>();
	private final Map<String, WidgetHandler> widgetHandlers = new HashMap<String, WidgetHandler>();

	private Template template = new Template();

	private String name;
	private T value;

	public TemplatePanel() {
		this("div");
	}

	public TemplatePanel(String tag) {
		this(tag, "");
	}

	public TemplatePanel(String tag, String html) {
		this(new Template(tag, "", html));
	}

	public TemplatePanel(Template template) {
		super(template.getTag(), "");
		this.template = template;
		addStyleName(Style.TEMPLATE_PANEL);
		if (template.getStyle() != null && !template.getStyle().isEmpty()) {
			addStyleName(template.getStyle());
		}
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public void add(Widget w) {
		try {
			add(w, getElement());
		} catch (Throwable e) {
			// TODO
			e.printStackTrace();
		}
	}

	public void addWidgetHandler(String name, WidgetHandler handler) {
		widgetHandlers.put(name, handler);
	}

	public void addWidgetHandler(String name, final Widget widget) {
		addWidgetHandler(name, new WidgetHandler() {
			@Override
			public Widget onWidget(String id) {
				return widget;
			}
		});
	}

	public void removeWidgetHandler(String name) {
		widgetHandlers.remove(name);
	}

	@Override
	protected void onAttach() {
		super.onAttach();
		onTemplate(template);
	}

	private void addWidgets() {
		clear();
		for (String template : widgetHandlers.keySet()) {
			ids.put(template, HTMLPanel.createUniqueId());
		}
		DOM.setInnerHTML(getElement(), TemplateUtils.replaceTemplate(template
				.getHtml(), ids));
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
		onAddWidgets();
	}

	public void onAddWidgets() {
	}

	public void template() {
		template(template);
	}

	public void template(Template template) {
		this.template = template;
		addWidgets();
	}

	@Override
	public void onFailure(Throwable e) {
		GWT.getUncaughtExceptionHandler().onUncaughtException(e);
	}

	@Override
	public void onTemplate(Template template) {
		template(template);
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
