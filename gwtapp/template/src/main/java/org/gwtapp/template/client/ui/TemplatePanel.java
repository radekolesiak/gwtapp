package org.gwtapp.template.client.ui;

import java.util.HashMap;
import java.util.Map;

import org.gwtapp.template.client.Template;
import org.gwtapp.template.client.TemplateUtils;
import org.gwtapp.template.client.WidgetHandler;

import com.google.gwt.dom.client.Element;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.HasName;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.Widget;

public class TemplatePanel<T> extends HTMLPanel implements HasValue<T>, HasName {

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
		this(new Template(tag, "", "", html));
	}

	public TemplatePanel(Template template) {
		super(template.getTag(), template.getHtml());
		this.template = template;
		addStyleName(Style.TEMPLATE_PANEL);
		if (template.getStyleClass() != null
				&& !template.getStyleClass().isEmpty()) {
			addStyleName(template.getStyleClass());
		}
		if (template.getStyle() != null && !template.getStyle().isEmpty()) {
			getElement().setAttribute("style", template.getStyle());
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
		template();
	}

	private boolean templated = false;

	private void template() {
		if (templated) {
			return;
		}
		templated = true;
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
