package org.gwtapp.template.client.ui;

import java.util.HashMap;
import java.util.Map;

import org.gwtapp.template.client.Template;
import org.gwtapp.template.client.TemplateHandler;
import org.gwtapp.template.client.callbacks.TFieldUniversalCallback;

import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.Element;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasChangeHandlers;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.DeferredCommand;
import com.google.gwt.user.client.ui.HasName;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.Widget;

public class TemplatePanel<T> extends HTMLPanel implements HasValue<T>,
		HasName, HasChangeHandlers, HasClickHandlers {

	public static interface Callback {
		void template(TemplatePanel<?> owner,
				Map<String, TemplateHandler> widgetHandlers);
	}

	public static interface TemplateCallback extends Callback {
		Template getTemplate();
	}

	public static interface ElementCallback extends Callback {
		Element getElement();
	}

	public static class Style {
		public final static String TEMPLATE_PANEL = "templatePanel";
	}

	private final Callback callback;
	private final Map<String, TemplateHandler> widgetHandlers = new HashMap<String, TemplateHandler>();

	private boolean initValueByDeferredCommand = false;
	private String pattern = "t:field";

	private String name;
	private T value;

	private boolean templated = false;
	private T initValue = null;

	public TemplatePanel() {
		this("div");
	}

	public TemplatePanel(String tag) {
		this(tag, "");
	}

	public TemplatePanel(String tag, String html) {
		this(new Template(tag, "", "", html));
	}

	public TemplatePanel(final Template template) {
		super(template.getTag(), template.getHtml());
		callback = new TFieldUniversalCallback(template);
		addStyleName(Style.TEMPLATE_PANEL);
		if (template.getStyleClass() != null
				&& !template.getStyleClass().isEmpty()) {
			addStyleName(template.getStyleClass());
		}
		if (template.getStyle() != null && !template.getStyle().isEmpty()) {
			getElement().setAttribute("style", template.getStyle());
		}
	}

	public TemplatePanel(Template template, T initValue) {
		this(template);
		setValue(initValue);
	}

	public TemplatePanel(ElementCallback callback) {
		super(callback.getElement());
		this.callback = callback;
	}

	public TemplatePanel(ElementCallback callback, T initValue) {
		this(callback);
		setValue(initValue);
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

	public <H extends TemplateHandler> H add(String name, H handler) {
		addWidgetHandler(name, handler);
		return handler;
	}

	public void addWidgetHandler(String name, TemplateHandler handler) {
		assert name != null;
		widgetHandlers.put(name, handler);
	}

	public void addWidgetHandler(String name, final Widget widget) {
		addWidgetHandler(name, new TemplateHandler() {
			@Override
			public Widget onWidget(String id) {
				return widget;
			}
		});
	}

	public void removeWidgetHandler(String name) {
		assert name != null;
		widgetHandlers.remove(name);
	}

	@Override
	protected void onAttach() {
		super.onAttach();
		template();
	}

	private void template() {
		if (templated) {
			return;
		}
		try {
			callback.template(this, widgetHandlers);
			onAddWidgets();
		} finally {
			templated = true;
			if (getInitValue() != null) {
				setInitValue();
			}
		}
	}

	public void onAddWidgets() {
	}

	public boolean isTemplated() {
		return templated;
	}

	@Override
	public T getValue() {
		if (getInitValue() == null) {
			return value;
		} else {
			return getInitValue();
		}
	}

	@Override
	public void setValue(T value) {
		setValue(value, false);
	}

	@Override
	public void setValue(T value, boolean fireEvents) {
		if (isTemplated()) {
			setInitValue(null);
			this.value = value;
			if (fireEvents) {
				ValueChangeEvent.fire(this, value);
			}
		} else {
			setInitValue(value);
		}
	}

	@Override
	public HandlerRegistration addValueChangeHandler(
			ValueChangeHandler<T> handler) {
		return addHandler(handler, ValueChangeEvent.getType());
	};

	public void setPattern(String pattern) {
		this.pattern = pattern;
	}

	public String getPattern() {
		return pattern;
	}

	public void fireChangeEvent() {
		ChangeEvent.fireNativeEvent(Document.get().createChangeEvent(), this);
	}

	@Override
	public HandlerRegistration addChangeHandler(ChangeHandler handler) {
		return addHandler(handler, ChangeEvent.getType());
	}

	@Override
	public HandlerRegistration addClickHandler(ClickHandler handler) {
		return addDomHandler(handler, ClickEvent.getType());
	}

	public void setInitValueByDeferredCommand(boolean initValueByDeferredCommand) {
		this.initValueByDeferredCommand = initValueByDeferredCommand;
	}

	public boolean isInitValueByDeferredCommand() {
		return initValueByDeferredCommand;
	}

	private void setInitValue() {
		if (isInitValueByDeferredCommand()) {
			DeferredCommand.addCommand(new Command() {
				@Override
				public void execute() {
					onDeferredInitValue();
				}
			});
		} else {
			onDeferredInitValue();
		}
	}

	private void onDeferredInitValue() {
		if (getInitValue() != null) {
			T localvalue = getInitValue();
			setInitValue(null);
			setValue(localvalue);
		}
	}

	private void setInitValue(T initValue) {
		this.initValue = initValue;
	}

	private T getInitValue() {
		return initValue;
	}
}
