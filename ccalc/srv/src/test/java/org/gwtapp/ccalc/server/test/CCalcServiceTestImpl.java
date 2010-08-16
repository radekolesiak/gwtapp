package org.gwtapp.ccalc.server.test;

import java.util.List;

import javax.persistence.Query;

import org.gwtapp.ccalc.client.api.CCalcService;
import org.gwtapp.ccalc.client.data.book.Book;
import org.gwtapp.ccalc.client.data.user.User;
import org.gwtapp.core.rpc.exception.RpcException;

@SuppressWarnings("serial")
public class CCalcServiceTestImpl extends RemoteServiceDBServlet implements
		CCalcService {

	@Override
	public void backup(Book book) throws RpcException {

	}

	@SuppressWarnings("unchecked")
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
			Query query = getEntityManager().createQuery(
					"SELECT u FROM CCalcUser u WHERE u.login = ?1");
			query.setParameter(1, login);
			List<User> users = query.getResultList();
			if (users.isEmpty()) {
				throw new RpcException();
			} else {
				return users.get(0);
			}
		}
	}
}
