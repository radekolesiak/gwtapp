package org.gwtapp.ccalc.rpc.api;

import org.gwtapp.ccalc.rpc.data.book.Book;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface CCalcServiceAsync {
	void backup(Book book, AsyncCallback<Void> callback);
}
