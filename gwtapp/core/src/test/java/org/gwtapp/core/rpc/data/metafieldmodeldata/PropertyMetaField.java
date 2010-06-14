package org.gwtapp.core.rpc.data.metafieldmodeldata;

import org.gwtapp.core.rpc.data.MetaFieldAdapter;

public class PropertyMetaField extends MetaFieldAdapter<ModelImpl, String> {

	public PropertyMetaField() {
		super("property");
	}

	@Override
	public String def() {
		return "value";
	}

	@Override
	public String get(ModelImpl model) {
		return model.getProperty();
	}

	@Override
	public void set(ModelImpl model, String value) {
		model.setProperty(value);
	}
}