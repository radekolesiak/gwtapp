package org.gwtapp.form.client.ui;

import java.util.HashMap;
import java.util.Map;

import org.gwtapp.core.rpc.data.MetaField;
import org.gwtapp.core.rpc.data.ModelData;
import org.gwtapp.template.client.UiHandler;
import org.gwtapp.template.client.ui.TemplateFormPanel;

import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.Widget;

public class TemplateModelPanel<T extends ModelData> extends
		TemplateFormPanel<T> {

	private Map<String, UiHandler<?>> fields = new HashMap<String, UiHandler<?>>();

	public TemplateModelPanel(TemplateCallback callback) {
		super(callback);
	}

	public TemplateModelPanel(TemplateCallback callback, T value) {
		super(callback, value);
	}

	public TemplateModelPanel(ElementCallback callback) {
		super(callback);
	}

	public TemplateModelPanel(ElementCallback callback, T value) {
		super(callback, value);
	}

	public <X, E extends Widget & HasValue<? extends X>> UiHandler<E> add(
			MetaField<?, ? extends X> metafield, UiHandler<E> handler) {
		addFieldHandler(metafield, handler);
		return handler;
	}

	public <X, E extends Widget & HasValue<? extends X>> void addFieldHandler(
			MetaField<?, ? extends X> autofield, UiHandler<E> handler) {
		assert autofield.name() != null && !autofield.name().isEmpty();
		add(autofield.name(), handler);
		fields.put(autofield.name(), handler);
	}

	public <X, E extends Widget & HasValue<? extends X>> void addFieldHandler(
			MetaField<?, ? extends X> autofield, E field) {
		add(autofield.name(), field);
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
