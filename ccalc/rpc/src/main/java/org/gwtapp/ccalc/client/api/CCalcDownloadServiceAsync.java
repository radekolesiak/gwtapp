package org.gwtapp.ccalc.client.api;

import org.gwtapp.ccalc.client.data.Book;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface CCalcDownloadServiceAsync {

	void download(Book book, AsyncCallback<Void> callback);
}
