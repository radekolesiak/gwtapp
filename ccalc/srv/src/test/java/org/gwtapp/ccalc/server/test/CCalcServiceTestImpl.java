package org.gwtapp.ccalc.server.test;

import java.util.Date;

import org.gwtapp.ccalc.rpc.api.CCalcService;
import org.gwtapp.ccalc.rpc.data.book.Book;
import org.gwtapp.ccalc.rpc.data.book.Currency;
import org.gwtapp.core.rpc.exception.NotImplementedException;
import org.gwtapp.core.rpc.exception.RpcException;

import com.google.inject.Inject;

@SuppressWarnings("serial")
public class CCalcServiceTestImpl extends RemoteServiceDBServlet implements
		CCalcService {

	@Inject
	CCalcService delegated;

	@Override
	public void backup(Book book) throws RpcException {
		getService().backup(book);
	}

	@Override
	public Double getRatio(Date date, Currency from, Currency to)
			throws RpcException {
		return getService().getRatio(date, from, to);
	}

	protected CCalcService getService() {
		if (delegated == null) {
			throw new NotImplementedException();
		} else {
			return delegated;
		}
	}
}
