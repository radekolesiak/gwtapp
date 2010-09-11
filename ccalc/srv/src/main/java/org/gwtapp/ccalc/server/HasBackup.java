package org.gwtapp.ccalc.server;

import org.gwtapp.ccalc.rpc.data.book.Book;
import org.gwtapp.core.rpc.exception.RpcException;

public interface HasBackup {
	void backup(Book book) throws RpcException;
}
