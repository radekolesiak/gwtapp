package org.gwtapp.ccalc.client.data.book.metafields.book;

import org.gwtapp.ccalc.client.data.book.Book;
import org.gwtapp.ccalc.client.data.book.Currency;
import org.gwtapp.core.rpc.data.MetaFieldAdapter;


public class BaseCurrencyMetaField extends MetaFieldAdapter<Book, Currency> {

	public BaseCurrencyMetaField() {
		this("baseCurrency");
	}

	public BaseCurrencyMetaField(String name) {
		super(name);
	}

	@Override
	public Currency get(Book model) {
		return model.getBaseCurrency();
	}

	@Override
	public void set(Book model, Currency value) {
		model.setBaseCurrency((Currency) value);
	}

	@Override
	public Currency def() {
		return Currency.PLN;
	}
}
