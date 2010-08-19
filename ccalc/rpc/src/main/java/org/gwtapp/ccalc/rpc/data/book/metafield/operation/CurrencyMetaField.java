package org.gwtapp.ccalc.rpc.data.book.metafield.operation;

import org.gwtapp.ccalc.rpc.data.book.Currency;
import org.gwtapp.ccalc.rpc.data.book.Operation;
import org.gwtapp.core.rpc.data.MetaFieldAdapter;


public class CurrencyMetaField extends MetaFieldAdapter<Operation, Currency> {

	public CurrencyMetaField() {
		this("currency");
	}

	public CurrencyMetaField(String name) {
		super(name);
	}

	@Override
	public Currency get(Operation model) {
		return model.getCurrency();
	}

	@Override
	public void set(Operation model, Currency value) {
		model.setCurrency((Currency) value);
	}
	
	@Override
	public Currency def() {
		return Currency.values()[0];
	}
}
