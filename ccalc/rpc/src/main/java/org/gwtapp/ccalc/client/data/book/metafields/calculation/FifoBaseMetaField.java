package org.gwtapp.ccalc.client.data.book.metafields.calculation;

import org.gwtapp.ccalc.client.data.book.Calculation;
import org.gwtapp.core.rpc.data.MetaFieldAdapter;


public class FifoBaseMetaField extends MetaFieldAdapter<Calculation, Double> {

	public FifoBaseMetaField() {
		this("fifoBase");
	}

	public FifoBaseMetaField(String name) {
		super(name);
	}

	@Override
	public Double get(Calculation model) {
		return model.getFifoBase();
	}

	@Override
	public void set(Calculation model, Double value) {
		model.setFifoBase((Double) value);
	}

}