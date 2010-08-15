package org.gwtapp.ccalc.client.data.book.metafields.operation;

import org.gwtapp.ccalc.client.data.book.Currency;
import org.gwtapp.ccalc.client.data.book.Operation;
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
