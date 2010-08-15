package org.gwtapp.ccalc.server;

import org.gwtapp.ccalc.client.api.CCalcService;
import org.gwtapp.ccalc.client.data.book.Book;
import org.gwtapp.ccalc.client.data.user.User;
import org.gwtapp.core.rpc.exception.RpcException;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

@SuppressWarnings("serial")
public class CCalcServiceImplTest extends RemoteServiceServlet implements
		CCalcService {

	@Override
	public void backup(Book book) throws RpcException {

	}

	@Override
	public User getUser(String login) throws RpcException {
		if (login == null) {
			throw new RpcException();
		} else if ("aba".equals(login)) {
			User user = new User();
			user.setId(1L);
			user.setLogin(login);
			user.setEmail(login + "@email.com");
			user.setName("Name: " + login);
			return user;
		} else if ("xyz".equals(login)) {
			return null;
		} else {
			return null;
		}
	}
}
