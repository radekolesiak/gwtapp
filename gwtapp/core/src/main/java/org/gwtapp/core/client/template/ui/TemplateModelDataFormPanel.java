package org.gwtapp.core.client.template.ui;

import java.util.Map;

import org.gwtapp.core.client.template.Template;
import org.gwtapp.core.rpc.data.ModelData;

import com.google.gwt.user.client.ui.HasValue;

public class TemplateModelDataFormPanel<T extends ModelData> extends TemplateFormPanel<T> {

	public TemplateModelDataFormPanel() {
		super();
	}

	public TemplateModelDataFormPanel(Template template) {
		super(template);
	}

	public TemplateModelDataFormPanel(T value) {
		this();
		setValue(value);
	}

	public TemplateModelDataFormPanel(T value, Template template) {
		this(template);
		setValue(value);
	}


	@SuppressWarnings("unchecked")
	@Override
	public T getValue() {
		T value = super.getValue();
		if (value != null) {
			for (Map.Entry<String, HasValue> entry : getFields().entrySet()) {
				value.set(entry.getKey(), entry.getValue().getValue());
			}
		}
		return value;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void setValue(T value, boolean fireEvents) {
		for (Map.Entry<String, HasValue> entry : getFields().entrySet()) {
			if (value != null) {
				entry.getValue().setValue(value.get(entry.getKey()));
			} else {
				entry.getValue().setValue(null);
			}
		}
		super.setValue(value, fireEvents);
	};
}
