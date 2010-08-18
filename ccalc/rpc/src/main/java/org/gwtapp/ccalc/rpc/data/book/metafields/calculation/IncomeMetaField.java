package org.gwtapp.ccalc.rpc.data.book.metafields.calculation;

import org.gwtapp.ccalc.rpc.data.book.Calculation;
import org.gwtapp.core.rpc.data.MetaFieldAdapter;


public class IncomeMetaField extends MetaFieldAdapter<Calculation, Double> {

	public IncomeMetaField() {
		this("income");
	}

	public IncomeMetaField(String name) {
		super(name);
	}

	@Override
	public Double get(Calculation model) {
		return model.getIncome();
	}

	@Override
	public void set(Calculation model, Double value) {
		model.setIncome((Double) value);
	}

}
