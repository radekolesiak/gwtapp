package org.gwtapp.ccalc.server;

import org.gwtapp.ccalc.rpc.api.CCalcService;
import org.gwtapp.ccalc.rpc.data.book.Book;
import org.gwtapp.ccalc.rpc.data.book.Currency;
import org.gwtapp.core.rpc.exception.NotImplementedException;
import org.gwtapp.core.rpc.exception.RpcException;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.google.inject.Inject;
import com.google.inject.Singleton;

@SuppressWarnings("serial")
@Singleton
public class CCalcServiceImpl extends RemoteServiceServlet implements
		CCalcService, HasBackup, HasCurrencyRatio {

	@Inject
	private HasBackup backupService;
	@Inject
	private HasCurrencyRatio currencyRatioService;

	@Override
	public void backup(Book book) throws RpcException {
		if (backupService != null) {
			backupService.backup(book);
		} else {
			throw new NotImplementedException();
		}
	}

	@Override
	public Double getRatio(int year, int month, int day, Currency from,
			Currency to) throws RpcException {
		if (currencyRatioService != null) {
			return currencyRatioService.getRatio(year, month, day, from, to);
		} else {
			throw new NotImplementedException();
		}
	}
}
