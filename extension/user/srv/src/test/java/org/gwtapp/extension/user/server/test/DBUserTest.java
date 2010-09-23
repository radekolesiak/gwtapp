package org.gwtapp.extension.user.server.test;

import java.util.List;

import javax.persistence.Query;
import javax.persistence.RollbackException;

import org.gwtapp.extension.user.client.data.User;
import org.gwtapp.extension.user.client.data.UserImpl;
import org.junit.Assert;
import org.junit.Test;

public class DBUserTest extends UserInit {

	@Test
	public void testInit() {
		Assert.assertNotNull(emf);
		Assert.assertNotNull(em);
	}

	@Test(expected = RollbackException.class)
	public void testCreateEmptyUser() {
		UserImpl user = new UserImpl();
		Assert.assertNull(user.getLogin());
		Assert.assertNull(user.getEmail());
		em.persist(user);
		tx.commit();
	}

	@Test
	public void testCreateUser() {
		UserImpl user = new UserImpl("a", "a");
		Assert.assertNotNull(user.getLogin());
		Assert.assertNotNull(user.getEmail());
		em.persist(user);
	}

	@Test(expected = RollbackException.class)
	public void testCreateUserDouble() {
		em.persist(new UserImpl("b", "b"));
		em.persist(new UserImpl("b", "b"));
		tx.commit();
	}

	@SuppressWarnings("unchecked")
	@Test
	public void testQueryParameters() {
		String q = "SELECT u FROM UserEntity u WHERE u.login = :login";
		String login = "a";
		Query query = em.createQuery(q);
		query.setMaxResults(1);
		query.setParameter("login", login);
		List<User> users = query.getResultList();
		Assert.assertNotNull(users);
		Assert.assertEquals(1, users.size());
		User user = users.get(0);
		Assert.assertEquals(login, user.getLogin());
	}
}
