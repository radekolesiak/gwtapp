package org.gwtapp.ccalc.client.data.metafields.book;

import org.gwtapp.ccalc.client.data.Book;
import org.gwtapp.ccalc.client.data.Currency;
import org.gwtapp.core.rpc.data.MetaFieldAdapter;


public class DefaultCurrencyMetaField extends MetaFieldAdapter<Book, Currency> {

	public DefaultCurrencyMetaField() {
		this("defaultCurrency");
	}

	public DefaultCurrencyMetaField(String name) {
		super(name);
	}

	@Override
	public Currency get(Book model) {
		return model.getDefaultCurrency();
	}

	@Override
	public void set(Book model, Currency value) {
		model.setDefaultCurrency((Currency) value);
	}

	@Override
	public Currency def() {
		return Currency.USD;
	}
}
