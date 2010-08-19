package org.gwtapp.ccalc.rpc.data.book.metafield.calculation;

import org.gwtapp.ccalc.rpc.data.book.Calculation;
import org.gwtapp.ccalc.rpc.data.book.Currency;
import org.gwtapp.core.rpc.data.MetaFieldAdapter;


public class FifoMetaField extends MetaFieldAdapter<Calculation, Double> {

	private final Currency currency;

	public FifoMetaField(Currency currency) {
		this(currency, "fifo" + currency);
	}

	public FifoMetaField(Currency currency, String name) {
		super(name);
		this.currency = currency;
	}

	public Currency getCurrency() {
		return currency;
	}

	@Override
	public Double get(Calculation model) {
		return model.getFifo(currency);
	}

	@Override
	public void set(Calculation model, Double value) {
		model.setFifo(currency, (Double) value);
	}

	public static FifoMetaField[] getFields() {
		FifoMetaField[] FIFO = new FifoMetaField[Currency.values().length];
		for (Currency currency : Currency.values()) {
			FIFO[currency.ordinal()] = new FifoMetaField(currency);
		}
		return FIFO;
	}

}
