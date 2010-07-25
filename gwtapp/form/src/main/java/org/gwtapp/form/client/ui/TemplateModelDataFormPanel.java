package org.gwtapp.form.client.ui;

import java.util.HashMap;
import java.util.Map;

import org.gwtapp.core.rpc.data.MetaField;
import org.gwtapp.core.rpc.data.ModelData;
import org.gwtapp.template.client.Template;
import org.gwtapp.template.client.UiHandler;
import org.gwtapp.template.client.ui.TemplateFormPanel;

import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.Widget;

public class TemplateModelDataFormPanel<T extends ModelData> extends
		TemplateFormPanel<T> {

	private Map<String, UiHandler<?>> fields = new HashMap<String, UiHandler<?>>();

	public TemplateModelDataFormPanel(Template template) {
		super(template);
	}

	public TemplateModelDataFormPanel(ElementCallback callback) {
		super(callback);
	}

	public TemplateModelDataFormPanel(ElementCallback callback, T value) {
		super(callback, value);
	}

	public TemplateModelDataFormPanel(Template template, T value) {
		super(template, value);
	}

	public <E extends Widget & HasValue<?>> UiHandler<E> add(
			MetaField<?, ?> metafield, UiHandler<E> handler) {
		addFieldHandler(metafield, handler);
		return handler;
	}

	public <E extends Widget & HasValue<?>> void addFieldHandler(
			MetaField<?, ?> autofield, UiHandler<E> handler) {
		assert autofield.name() != null;
		addWidgetHandler(autofield.name(), handler);
		fields.put(autofield.name(), handler);
	}

	public <E extends Widget & HasValue<?>> void addFieldHandler(
			MetaField<?, ?> autofield, E field) {
		addWidgetHandler(autofield.name(), field);
		addField(autofield.name(), field);
	}

	@Override
	public final void onAddWidgets() {
		super.onAddWidgets();
		for (Map.Entry<String, UiHandler<?>> entry : fields.entrySet()) {
			addField(entry.getKey(), (HasValue<?>) entry.getValue().getWidget());
		}
		onAddFormWidgets();
	}

	public void onAddFormWidgets() {
	}

	@SuppressWarnings("unchecked")
	@Override
	public T getValue() {
		T value = super.getValue();
		// TODO this one should be cached by HasValueChangeHandlers and local
		// variable, it's faster.
		if (isTemplated() && value != null) {
			for (Map.Entry<String, HasValue> entry : getFields().entrySet()) {
				value.set(entry.getKey(), entry.getValue().getValue());
			}
		}
		return value;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void setValue(T value, boolean fireEvents) {
		if (isTemplated()) {
			// TODO test if this loop should executed by the DeferredCommand
			for (Map.Entry<String, HasValue> entry : getFields().entrySet()) {
				if (value != null) {
					entry.getValue().setValue(value.get(entry.getKey()));
				} else {
					entry.getValue().setValue(null);
				}
			}
		}
		super.setValue(value, fireEvents);
	};
}
