package org.gwtapp.ccalc.client.data.metafields.operation;

import org.gwtapp.ccalc.client.data.Operation;
import org.gwtapp.core.rpc.data.MetaFieldAdapter;


public class ExchangeMetaField extends MetaFieldAdapter<Operation, Double> {

	public ExchangeMetaField() {
		this("exchange");
	}

	public ExchangeMetaField(String name) {
		super(name);
	}

	@Override
	public Double get(Operation model) {
		return model.getExchange();
	}

	@Override
	public void set(Operation model, Double value) {
		model.setExchange((Double) value);
	}
}
