package org.gwtapp.ccalc.rpc.data.book.metafields.operation;

import org.gwtapp.ccalc.rpc.data.book.Operation;
import org.gwtapp.core.rpc.data.MetaFieldAdapter;


public class NameMetaField extends MetaFieldAdapter<Operation, String> {

	public NameMetaField() {
		this("name");
	}

	public NameMetaField(String name) {
		super(name);
	}

	@Override
	public String get(Operation model) {
		return model.getName();
	}

	@Override
	public void set(Operation model, String value) {
		model.setName((String) value);
	}
}
