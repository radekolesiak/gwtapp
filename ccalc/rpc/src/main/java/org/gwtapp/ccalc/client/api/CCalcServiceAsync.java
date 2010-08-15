package org.gwtapp.ccalc.client.api;

import org.gwtapp.ccalc.client.data.Book;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("ccalc.rpc")
public interface CCalcServiceAsync {

	void backup(Book book, AsyncCallback<Void> callback);
}
