package org.gwtapp.ccalc.server.test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;

public abstract class CCalcInit {

	protected static EntityManagerFactory emf;
	protected static EntityManager em;
	protected EntityTransaction tx;

	@BeforeClass
	public static void setUp() {
		emf = Persistence.createEntityManagerFactory("derbyPU");
		em = emf.createEntityManager();
	}

	@Before
	public void openTx() {
		tx = em.getTransaction();
		tx.begin();
	}

	@After
	public void closeTx() {
		if (tx.isActive()) {
			tx.commit();
		}
	}

	@AfterClass
	public static void tearDown() {
		em.close();
		emf.close();
	}
}
