package org.gwtapp.ccalc.client.data.book.metafields.operation;

import org.gwtapp.ccalc.client.data.book.Operation;
import org.gwtapp.core.rpc.data.MetaFieldAdapter;


public class ValueMetaField extends MetaFieldAdapter<Operation, Double> {

	public ValueMetaField() {
		this("value");
	}

	public ValueMetaField(String name) {
		super(name);
	}

	@Override
	public Double get(Operation model) {
		return model.getValue();
	}

	@Override
	public void set(Operation model, Double value) {
		model.setValue((Double) value);
	}
}
