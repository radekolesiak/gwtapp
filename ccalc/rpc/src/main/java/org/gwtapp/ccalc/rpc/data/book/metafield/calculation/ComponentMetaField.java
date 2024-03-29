package org.gwtapp.ccalc.rpc.data.book.metafield.calculation;

import org.gwtapp.ccalc.rpc.data.book.Calculation;
import org.gwtapp.core.rpc.data.MetaFieldAdapter;


public class ComponentMetaField extends MetaFieldAdapter<Calculation, Double> {

	public ComponentMetaField() {
		this("component");
	}

	public ComponentMetaField(String name) {
		super(name);
	}

	@Override
	public Double get(Calculation model) {
		return model.getComponent();
	}

	@Override
	public void set(Calculation model, Double value) {
		model.setComponent((Double) value);
	}

}
