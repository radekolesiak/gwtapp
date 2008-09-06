package com.cuprum.server.common.entities;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.junit.Test;

public class SessionTest extends EntityTestDAO {
	static final Logger LOGGER = Logger.getLogger(SessionTest.class);

	private final static String LOGIN1 = "testdLogin1";

	private final static String LOGIN2 = "testdLogin2";

	private final static String PASSWORD = "password";

	private final static String MAIL = "mail@mail.com";

	@Test
	public void SessionTest1() {
		User user = new User();
		user.setLogin(LOGIN1);
		user.setPassword(PASSWORD);
		user.setMail(MAIL);

		UserSession session1 = new UserSession();
		session1.setSession("s1");
		session1.setDate(new Date());

		session1.setUser(user);

		getBean().save(user);
		getBean().save(session1);

		restart();

		// Session session1b = getBean().load(Session.class, session1.getId());
		UserSession session1b = getBean().get(UserSession.class,
				session1.getId());

		assertEquals(session1.getUser().getId(), session1b.getUser().getId());
		assertFalse(session1.getUser() == session1b.getUser());
	}

	@Test
	public void SessionTest2() {
		User user1 = new User();
		user1.setLogin(LOGIN1);
		user1.setPassword(PASSWORD);
		user1.setMail(MAIL);

		User user2 = new User();
		user2.setLogin(LOGIN2);
		user2.setPassword(PASSWORD);
		user2.setMail(MAIL);

		UserSession session1 = new UserSession();
		session1.setSession("s1");
		session1.setDate(new Date());
		session1.setUser(user2);

		UserSession session2 = new UserSession();
		session2.setSession("s2");
		session2.setDate(new Date());
		session2.setUser(user1);

		getBean().save(user1);
		getBean().save(user2);
		getBean().save(session1);
		getBean().save(session2);

		restart();

		// Session session1b = getBean().load(Session.class, session1.getId());
		// Session session2b = getBean().get(Session.class, session2.getId());

		UserSession session1b = getBean().get(UserSession.class,
				session1.getId());
		UserSession session2b = getBean().get(UserSession.class,
				session2.getId());

		assertEquals(session1.getUser().getId(), session1b.getUser().getId());
		assertFalse(session1.getUser() == session1b.getUser());

		assertEquals(session2.getUser().getId(), session2b.getUser().getId());
		assertFalse(session2.getUser() == session2b.getUser());
	}

	@Test
	public void SessionTest3() {
		User user1 = new User();
		user1.setLogin(LOGIN1);
		user1.setPassword(PASSWORD);
		user1.setMail(MAIL);

		User user2 = new User();
		user2.setLogin(LOGIN2);
		user2.setPassword(PASSWORD);
		user2.setMail(MAIL);

		UserSession session1 = new UserSession();
		session1.setSession("s1");
		session1.setDate(new Date());
		session1.setUser(user2);

		UserSession session2 = new UserSession();
		session2.setSession("s2");
		session2.setDate(new Date());
		session2.setUser(user1);

		getBean().save(user1);
		getBean().save(user2);
		getBean().save(session1);
		getBean().save(session2);

		restart();

		String query = "from User where login='" + LOGIN1 + "' and password='"
				+ PASSWORD + "A'";

		List list = getBean().createQuery(query);

		for (Object item : list) {
			User user = (User) item;
			LOGGER.info(user.getLogin());
		}
	}
}
