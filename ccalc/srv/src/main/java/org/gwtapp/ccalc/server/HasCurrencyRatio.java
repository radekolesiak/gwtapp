package org.gwtapp.ccalc.server;

import org.gwtapp.ccalc.rpc.data.book.Currency;
import org.gwtapp.core.rpc.exception.RpcException;

public interface HasCurrencyRatio {
	Double getRatio(int year, int month, int day, Currency from, Currency to)
			throws RpcException;
}
