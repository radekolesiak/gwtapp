package org.gwtapp.ccalc.rpc.data.book.metafield.fetchedratio;

import org.gwtapp.ccalc.rpc.data.book.Currency;
import org.gwtapp.ccalc.rpc.data.book.FetchedRatio;
import org.gwtapp.core.rpc.data.MetaFieldAdapter;

public class BaseCurrencyMetaField extends MetaFieldAdapter<FetchedRatio, Currency> {
	public BaseCurrencyMetaField() {
		super("basecurrency");
	}

	@Override
	public void set(FetchedRatio model, Currency value) {
		model.setBaseCurrency(value);
	}

	@Override
	public Currency get(FetchedRatio model) {
		return model.getBaseCurrency();
	}
}
