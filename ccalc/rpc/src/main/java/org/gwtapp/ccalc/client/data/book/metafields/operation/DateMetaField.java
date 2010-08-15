package org.gwtapp.ccalc.client.data.book.metafields.operation;

import java.util.Date;

import org.gwtapp.ccalc.client.data.book.Operation;
import org.gwtapp.core.rpc.data.MetaFieldAdapter;


public class DateMetaField extends MetaFieldAdapter<Operation, Date> {

	public DateMetaField() {
		this("date");
	}

	public DateMetaField(String name) {
		super(name);
	}

	@Override
	public Date get(Operation model) {
		return model.getDate();
	}

	@Override
	public void set(Operation model, Date value) {
		model.setDate((Date) value);
	}
}
