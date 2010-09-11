package org.gwtapp.ccalc.server.test;

import java.util.Date;

import org.gwtapp.ccalc.rpc.api.CCalcService;
import org.gwtapp.ccalc.rpc.data.book.Book;
import org.gwtapp.ccalc.rpc.data.book.Currency;
import org.gwtapp.core.rpc.exception.NotImplementedException;
import org.gwtapp.core.rpc.exception.RpcException;

@SuppressWarnings("serial")
public class CCalcServiceTestImpl extends RemoteServiceDBServlet implements
		CCalcService {

	@Override
	public void backup(Book book) throws RpcException {
		throw new NotImplementedException();
	}

	@Override
	public Double getRatio(Date date, Currency from, Currency to)
			throws RpcException {
		throw new NotImplementedException();
	}
}
