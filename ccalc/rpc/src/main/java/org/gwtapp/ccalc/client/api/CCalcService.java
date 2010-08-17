package org.gwtapp.ccalc.client.api;

import org.gwtapp.ccalc.client.data.book.Book;
import org.gwtapp.core.rpc.exception.RpcException;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("ccalc.rpc")
public interface CCalcService extends RemoteService {
	void backup(Book book) throws RpcException;
}
