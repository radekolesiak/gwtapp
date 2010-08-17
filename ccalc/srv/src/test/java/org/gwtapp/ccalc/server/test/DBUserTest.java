package org.gwtapp.ccalc.server.test;

import javax.persistence.RollbackException;

import org.gwtapp.extension.user.client.data.User;
import org.junit.Assert;
import org.junit.Test;

public class DBUserTest extends CCalcTest {

	@Test
	public void testInit() {
		Assert.assertNotNull(emf);
		Assert.assertNotNull(em);
	}

	@Test(expected = RollbackException.class)
	public void testCreateEmptyUser() {
		User user = new User();
		Assert.assertNull(user.getLogin());
		Assert.assertNull(user.getEmail());
		Assert.assertNull(user.getName());
		em.persist(user);
		tx.commit();
	}

	@Test
	public void testCreateUser() {
		User user = new User("a", "a", "a");
		Assert.assertNotNull(user.getLogin());
		Assert.assertNotNull(user.getEmail());
		Assert.assertNotNull(user.getName());
		em.persist(user);
	}

	@Test(expected = RollbackException.class)
	public void testCreateUserDouble() {
		em.persist(new User("b", "b", "b"));
		em.persist(new User("b", "b", "b"));
		tx.commit();
	}
}
