package org.gwtapp.ccalc.client.api;

import org.gwtapp.ccalc.client.data.book.Book;
import org.gwtapp.core.rpc.exception.RpcException;
import org.gwtapp.extension.user.client.data.User;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("ccalc.rpc")
public interface CCalcService extends RemoteService {

	void backup(Book book) throws RpcException;

	User getUser(String login) throws RpcException;

	long addUser(User user) throws RpcException;
}
