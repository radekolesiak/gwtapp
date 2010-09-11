package org.gwtapp.ccalc.server;

import java.util.Date;

import org.gwtapp.ccalc.rpc.data.book.Currency;
import org.gwtapp.core.rpc.exception.RpcException;

public interface HasCurrencyRatio {
	Double getRatio(Date date, Currency from, Currency to) throws RpcException;
}
