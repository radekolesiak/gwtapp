package org.gwtapp.ccalc.client.data.book.metafields.calculation;

import org.gwtapp.ccalc.client.data.book.Calculation;
import org.gwtapp.core.rpc.data.MetaFieldAdapter;


public class CostMetaField extends MetaFieldAdapter<Calculation, Double> {

	public CostMetaField() {
		this("cost");
	}

	public CostMetaField(String name) {
		super(name);
	}

	@Override
	public Double get(Calculation model) {
		return model.getCost();
	}

	@Override
	public void set(Calculation model, Double value) {
		model.setCost((Double) value);
	}

}
