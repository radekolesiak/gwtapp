package org.gwtapp.ccalc.rpc.data.book.metafield.fetchedratio;

import org.gwtapp.ccalc.rpc.data.book.Currency;
import org.gwtapp.ccalc.rpc.data.book.FetchedRatio;
import org.gwtapp.core.rpc.data.MetaFieldAdapter;

public class CurrencyMetaField extends MetaFieldAdapter<FetchedRatio, Currency> {
	public CurrencyMetaField() {
		super("currency");
	}

	@Override
	public void set(FetchedRatio model, Currency value) {
		model.setCurrency(value);
	}

	@Override
	public Currency get(FetchedRatio model) {
		return model.getCurrency();
	}
}
