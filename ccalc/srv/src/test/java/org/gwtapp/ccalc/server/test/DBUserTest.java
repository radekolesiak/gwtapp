package org.gwtapp.ccalc.server.test;

import org.gwtapp.ccalc.client.data.user.User;
import org.junit.Assert;
import org.junit.Test;

public class DBUserTest extends CCalcTest {

	@Test
	public void testInit() {
		Assert.assertNotNull(emf);
		Assert.assertNotNull(em);
	}

	@Test
	public void testCreateUser() {
		User user = new User();
		em.persist(user);
	}
}
