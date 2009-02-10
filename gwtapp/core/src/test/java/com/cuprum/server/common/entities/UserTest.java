package com.cuprum.server.common.entities;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.orm.hibernate3.HibernateObjectRetrievalFailureException;

import com.cuprum.server.common.utils.EntityTestDAO;

public class UserTest extends EntityTestDAO {
	/**
	 * Logger for this class.
	 */
	static final Logger LOGGER = Logger.getLogger(UserTest.class);

	private final static String LOGIN1 = "testdLogin1";

	private final static String LOGIN2 = "testdLogin2";

	private final static String PASSWORD = "password";

	private final static String MAIL = "mail@mail.com";

	public static User createUser1() {
		User user = new User();
		user.setLogin(LOGIN1);
		user.setPassword(PASSWORD);
		user.setMail(MAIL);
		return user;
	}
	
	public static User createUser2() {
		User user = new User();
		user.setLogin(LOGIN2);
		user.setPassword(PASSWORD);
		user.setMail(MAIL);
		return user;
	}
	
	/**
	 * Inserts first user.
	 */
	@Test
	public final void InsertValue1a() {
		User user = createUser1();
		getBean().save(user);
	}

	/**
	 * Inserts second user with another login and same password.
	 */
	@Test
	public final void InsertValue1b() {
		User user1 = createUser1();
		User user2 = createUser2();
		
		getBean().save(user1);
		getBean().save(user2);
	}

	/**
	 * Inserts user with first login and same password.
	 */
	@Test(expected = DataIntegrityViolationException.class)
	public final void InsertValue1c() {
		User user1 = createUser1();

		User user2 = createUser1();

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
		User user1 = createUser1();

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
		User user1 = createUser1();

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
