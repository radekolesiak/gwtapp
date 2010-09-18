package org.gwtapp.ccalc.rpc.api;

import org.gwtapp.ccalc.rpc.data.book.Book;
import org.gwtapp.ccalc.rpc.data.book.Currency;
import org.gwtapp.core.rpc.exception.RpcException;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("ccalc.rpc")
public interface CCalcService extends RemoteService {
	
	void backup(Book book) throws RpcException;

	Double getRatio(int year, int month, int day, Currency from, Currency to) throws RpcException;
}
