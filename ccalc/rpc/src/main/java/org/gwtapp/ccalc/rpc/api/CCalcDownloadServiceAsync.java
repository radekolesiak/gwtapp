package org.gwtapp.ccalc.rpc.api;

import org.gwtapp.ccalc.rpc.data.book.Book;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface CCalcDownloadServiceAsync {

	void download(Book book, AsyncCallback<Void> callback);
}
