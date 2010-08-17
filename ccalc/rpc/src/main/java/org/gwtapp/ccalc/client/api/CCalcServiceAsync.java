package org.gwtapp.ccalc.client.api;

import org.gwtapp.ccalc.client.data.book.Book;
import org.gwtapp.extension.user.client.data.User;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface CCalcServiceAsync {

	void backup(Book book, AsyncCallback<Void> callback);

	void getUser(String login, AsyncCallback<User> callback);

	void addUser(User user, AsyncCallback<Long> callback);
}
