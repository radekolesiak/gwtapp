package com.cuprum.server.common.entities;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.orm.hibernate3.HibernateObjectRetrievalFailureException;

public class UserTest extends EntityTestDAO {
	/**
	 * Logger for this class.
	 */
	static final Logger LOGGER = Logger.getLogger(UserTest.class);

	private final static String LOGIN1 = "testdLogin1";

	private final static String LOGIN2 = "testdLogin2";

	private final static String PASSWORD = "password";

	private final static String MAIL = "mail@mail.com";
	
	/**
	 * Inserts first user.
	 */
	@Test
	public final void InsertValue1a() {
		User user = new User();
		user.setLogin(LOGIN1);
		user.setPassword(PASSWORD);
		user.setMail(MAIL);

		getBean().save(user);
	}

	/**
	 * Inserts second user with another login and same password.
	 */
	@Test
	public final void InsertValue1b() {
		User user1 = new User();
		user1.setLogin(LOGIN1);
		user1.setPassword(PASSWORD);
		user1.setMail(MAIL);

		User user2 = new User();
		user2.setLogin(LOGIN2);
		user2.setPassword(PASSWORD);
		user2.setMail(MAIL);

		getBean().save(user1);
		getBean().save(user2);
	}

	/**
	 * Inserts user with first login and same password.
	 */
	@Test(expected = DataIntegrityViolationException.class)
	public final void InsertValue1c() {
		User user1 = new User();
		user1.setLogin(LOGIN1);
		user1.setPassword(PASSWORD);

		User user2 = new User();
		user2.setLogin(LOGIN1);
		user2.setPassword(PASSWORD);

		try {
			getBean().save(user1);
			getBean().save(user2);
		} catch (DataIntegrityViolationException e) {
			LOGGER.debug("InsertValue1c::exception");
			getBean().exceptionTX();
			throw e;
		}
	}

	/**
	 * Removes user by force id value.
	 */
	@Test
	public final void RemoveValue1a() {
		User user1 = new User();
		user1.setLogin(LOGIN1);
		user1.setPassword(PASSWORD);
		user1.setMail(MAIL);

		getBean().save(user1);

		restart();

		User user2 = getBean().get(User.class, 1L);
		getBean().delete(user2);
		assertFalse(getBean().contains(user2));
		assertNull(getBean().get(User.class, 1L));
	}

	/**
	 * Double Remove user by force id value.
	 */
	@Test(expected = HibernateObjectRetrievalFailureException.class)
	public final void RemoveValue1b() {
		User user1 = new User();
		user1.setLogin(LOGIN1);
		user1.setPassword(PASSWORD);
		user1.setMail(MAIL);

		getBean().save(user1);
		//assertTrue(getBean().contains(user1));
		assertNotNull(user1.getId());
		assertNotNull(getBean().get(User.class, user1.getId()));

		restart();

		Long id = user1.getId();
		User user2 =  getBean().get(User.class, id);
		assertEquals(id, user2.getId());
		//assertTrue(getBean().contains(user2));
		assertNotNull(getBean().get(User.class, user2.getId()));
		getBean().delete(user2);
		//assertFalse(getBean().contains(user2));

		restart();

		User user3 = getBean().load(User.class, id);
		assertNotNull(user3.getId());
		assertNull(getBean().get(User.class, id));
		getBean().delete(user3);
	}
}
