package org.gwtapp.ccalc.server.test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.gwtapp.ccalc.client.data.user.User;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class DBUserTest {

	private static EntityManagerFactory emf;
	private static EntityManager em;

	@BeforeClass
	public static void setUp() {
		emf = Persistence.createEntityManagerFactory("derbyPU");
		em = emf.createEntityManager();
	}

	@Test
	public void testInit() {
		Assert.assertNotNull(emf);
		Assert.assertNotNull(em);
	}

	@Test
	public void testCreateUser() {
		User user = new User();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		em.persist(user);
		tx.commit();
	}

	@AfterClass
	public static void tearDown() {
		em.close();
		emf.close();
	}
}
