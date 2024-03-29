package org.gwtapp.template.client.ui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.gwtapp.core.client.AsyncCallbackInjector;
import org.gwtapp.core.client.pipe.PipeManager;
import org.gwtapp.core.client.ui.HasEnable;
import org.gwtapp.core.rpc.data.Value;
import org.gwtapp.template.client.HasTemplateValue;
import org.gwtapp.template.client.Template;
import org.gwtapp.template.client.callback.SimpleTemplateCallback;
import org.gwtapp.template.client.handler.TemplateHandler;
import org.gwtapp.template.client.handler.UiHandler;
import org.gwtapp.template.client.handler.ValidationHandler;
import org.gwtapp.validation.rpc.exception.ValidationException;

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
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasName;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;

public class TemplatePanel<T> extends HTMLPanel implements HasValue<T>, HasTemplateValue<T>, HasName, HasChangeHandlers, HasClickHandlers, HasEnable {

	public static interface Callback {
		void template(TemplatePanel<?> owner, Map<String, TemplateHandler> widgetHandlers);
	}

	public static interface TemplateCallback extends Callback {
		Template getTemplate();
	}

	public static interface ElementCallback extends Callback {
		Element getElement();
	}

	public static interface WidgetsCallback {
		void onWidgets();
	}

	protected static class TemplatePanelValueChangeEvent<T> extends ValueChangeEvent<T> {
		public TemplatePanelValueChangeEvent(T value) {
			super(value);
		}
	}

	public static class Style {
		public final static String TEMPLATE_PANEL = "template-panel";
	}

	private final Callback callback;

	private final Map<String, TemplateHandler> widgetHandlers = new HashMap<String, TemplateHandler>();

	private ValidationHandler<ValidationException> validator = new ValidationHandler<ValidationException>();

	private String name;

	private T value;

	private boolean templated = false;

	private Value<T> initValue = null;

	private T injectInitValue = null;

	private final PipeManager pipeManager = new PipeManager();

	private final List<WidgetsCallback> widgetsCallbacks = new ArrayList<WidgetsCallback>();

	private boolean enabled = true;

	@Inject(optional = true)
	private AsyncCallbackInjector asyncCallbackInjector;

	public TemplatePanel() {
		this(new SimpleTemplateCallback());
	}

	public TemplatePanel(TemplateCallback callback) {
		super(callback.getTemplate().getTag(), callback.getTemplate().getHtml());
		this.callback = callback;
		addStyleName(Style.TEMPLATE_PANEL);
		if (callback.getTemplate().getStyleClass() != null && !callback.getTemplate().getStyleClass().isEmpty()) {
			addStyleName(callback.getTemplate().getStyleClass());
		}
		if (callback.getTemplate().getStyle() != null && !callback.getTemplate().getStyle().isEmpty()) {
			getElement().setAttribute("style", callback.getTemplate().getStyle());
		}
		init();
	}

	public TemplatePanel(TemplateCallback callback, T initValue) {
		this(callback);
		setValue(initValue);
	}

	public TemplatePanel(ElementCallback callback) {
		super(callback.getElement());
		this.callback = callback;
		init();
	}

	public TemplatePanel(ElementCallback callback, T initValue) {
		this(callback);
		setValue(initValue);
	}

	private void init() {
		add("validator", validator);
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
		add(w, getElement());
	}

	public <W extends Widget> UiHandler<W> add(String name, final W widget) {
		return add(name, new UiHandler<W>(widget));
	}

	public <H extends TemplateHandler & HasName> H add(H handler) {
		return add(handler.getName(), handler);
	}

	public <H extends TemplateHandler> H add(String name, H handler) {
		assert name != null && !name.isEmpty();
		widgetHandlers.put(name, handler);
		return handler;
	}

	@Override
	protected void onAttach() {
		super.onAttach();
		getPipeManager().connect();
		template();
	}

	@Override
	protected void onDetach() {
		super.onDetach();
		getPipeManager().disconnect();
	}

	private void template() {
		if (templated) {
			return;
		}
		try {
			callback.template(this, widgetHandlers);
			onWidgets();
			for (WidgetsCallback callback : widgetsCallbacks) {
				callback.onWidgets();
			}
			widgetsCallbacks.clear();
		} finally {
			templated = true;
			if (getInitValue() != null) {
				setInitValue();
			}
		}
	}

	public void onWidgets() {
	}

	public boolean isTemplated() {
		return templated;
	}

	@Override
	public T getValue() {
		if (getInitValue() == null) {
			if (isTemplated()) {
				return getTemplateValue();
			} else {
				return value;
			}
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
			setTemplateValue(value);
			if (fireEvents) {
				ValueChangeEvent.fire(this, value);
			}
		} else {
			setInitValue(value);
		}
	}

	@Override
	public HandlerRegistration addValueChangeHandler(ValueChangeHandler<T> handler) {
		return addHandler(handler, ValueChangeEvent.getType());
	};

	public void addWidgetsCallback(WidgetsCallback callback) {
		widgetsCallbacks.add(callback);
	}

	public void fireChangeEvent() {
		ChangeEvent.fireNativeEvent(Document.get().createChangeEvent(), this);
	}

	public void fireValueChangeEvent() {
		ValueChangeEvent.fire(this, getValue());
	}

	@Override
	public HandlerRegistration addChangeHandler(ChangeHandler handler) {
		return addHandler(handler, ChangeEvent.getType());
	}

	@Override
	public HandlerRegistration addClickHandler(ClickHandler handler) {
		return addDomHandler(handler, ClickEvent.getType());
	}

	public void setAsyncCallbackInjector(AsyncCallbackInjector asyncCallbackInjector) {
		this.asyncCallbackInjector = asyncCallbackInjector;
	}

	public AsyncCallbackInjector getAsyncCallbackInjector() {
		return asyncCallbackInjector;
	}

	public <Q> AsyncCallback<Q> create(AsyncCallback<Q> callback) {
		AsyncCallbackInjector injector = getAsyncCallbackInjector();
		if (injector != null) {
			return injector.create(callback);
		} else {
			return callback;
		}
	}

	public ValidationHandler<ValidationException> getValidator() {
		return validator;
	}

	public PipeManager getPipeManager() {
		return pipeManager;
	}

	@Override
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
		for (int i = 0; i < getWidgetCount(); i++) {
			Widget widget = getWidget(i);
			if (widget instanceof HasEnable) {
				((HasEnable) widget).setEnabled(enabled);
			}
		}
	}

	@Override
	public boolean isEnabled() {
		return enabled;
	}

	@Override
	public void setTemplateValue(T value) {
	}

	@Override
	public T getTemplateValue() {
		return value;
	}

	private void setInitValue() {
		if (getInitValue() != null) {
			T localvalue = getInitValue();
			setInitValue(null);
			setValue(localvalue);
		}
	}

	private void setInitValue(T initValue) {
		this.initValue = new Value<T>(initValue);
	}

	private T getInitValue() {
		if (initValue != null) {
			return initValue.get();
		} else {
			return injectInitValue;
		}
	}
}
