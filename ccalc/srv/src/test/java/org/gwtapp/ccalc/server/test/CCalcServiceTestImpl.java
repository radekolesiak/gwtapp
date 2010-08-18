package org.gwtapp.ccalc.server.test;

import org.gwtapp.ccalc.client.api.CCalcService;
import org.gwtapp.ccalc.client.data.book.Book;
import org.gwtapp.core.rpc.exception.NotImplementedException;
import org.gwtapp.core.rpc.exception.RpcException;

@SuppressWarnings("serial")
public class CCalcServiceTestImpl extends RemoteServiceDBServlet implements
		CCalcService {

	@Override
	public void backup(Book book) throws RpcException {
		throw new NotImplementedException();
	}
}
