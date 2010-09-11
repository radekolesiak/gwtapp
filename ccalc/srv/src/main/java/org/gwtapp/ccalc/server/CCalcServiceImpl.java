package org.gwtapp.ccalc.server;

import java.util.Date;

import org.gwtapp.ccalc.rpc.api.CCalcService;
import org.gwtapp.ccalc.rpc.data.book.Book;
import org.gwtapp.ccalc.rpc.data.book.Currency;
import org.gwtapp.core.rpc.exception.NotImplementedException;
import org.gwtapp.core.rpc.exception.RpcException;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.google.inject.Inject;

@SuppressWarnings("serial")
public class CCalcServiceImpl extends RemoteServiceServlet implements
		CCalcService {

	@Inject
	private CCalcService delegated;

	@Override
	public void backup(Book book) throws RpcException {
		getDelegated().backup(book);
	}

	@Override
	public Double getRatio(Date date, Currency from, Currency to)
			throws RpcException {
		return getDelegated().getRatio(date, from, to);
	}

	protected CCalcService getDelegated() {
		if (delegated != null) {
			return delegated;
		} else {
			throw new NotImplementedException();
		}
	}
}
