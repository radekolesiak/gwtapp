package org.gwtapp.ccalc.rpc.api;

import org.gwtapp.ccalc.rpc.data.book.Book;
import org.gwtapp.ccalc.rpc.data.book.Currency;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface CCalcServiceAsync {

	void backup(Book book, AsyncCallback<Void> callback);

	void getRatio(int year, int month, int day, Currency from, Currency to,
			AsyncCallback<Double> callback);
}
