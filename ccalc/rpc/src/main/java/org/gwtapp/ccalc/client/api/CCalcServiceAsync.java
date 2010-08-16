package org.gwtapp.ccalc.client.api;

import org.gwtapp.ccalc.client.data.book.Book;
import org.gwtapp.ccalc.client.data.user.User;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("ccalc.rpc")
public interface CCalcServiceAsync {

	void backup(Book book, AsyncCallback<Void> callback);

	void getUser(String login, AsyncCallback<User> callback);

	void addUser(User user, AsyncCallback<Long> callback);
}
