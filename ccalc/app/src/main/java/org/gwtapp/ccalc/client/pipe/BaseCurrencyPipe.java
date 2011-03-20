package org.gwtapp.ccalc.client.pipe;

import org.gwtapp.ccalc.rpc.data.book.Currency;
import org.gwtapp.core.client.pipe.Pipe;
import org.gwtapp.core.client.pipe.PipeHandler;

public class BaseCurrencyPipe extends Pipe<Currency> {
	public BaseCurrencyPipe(PipeHandler<Currency> handler) {
		super(handler);
	}
}
