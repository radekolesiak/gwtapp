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
	CCalcService delegated;

	@Override
	public void backup(Book book) throws RpcException {
		if (delegated != null) {
			delegated.backup(book);
		} else {
			throw new NotImplementedException();
		}
	}

	@Override
	public Double getRatio(Date date, Currency from, Currency to)
			throws RpcException {
		if (delegated != null) {
			return delegated.getRatio(date, from, to);
		} else {
			throw new NotImplementedException();
		}
	}
}
