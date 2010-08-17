package org.gwtapp.ccalc.client.api;

import org.gwtapp.ccalc.client.data.book.Book;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface CCalcServiceAsync {
	void backup(Book book, AsyncCallback<Void> callback);
}
