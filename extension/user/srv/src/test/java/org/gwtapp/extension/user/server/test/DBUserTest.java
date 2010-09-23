package org.gwtapp.extension.user.server.test;

import javax.persistence.RollbackException;

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
}
