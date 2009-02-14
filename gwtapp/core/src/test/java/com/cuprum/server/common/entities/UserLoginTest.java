package com.cuprum.server.common.entities;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.apache.log4j.Logger;
import org.junit.Test;

//import com.cuprum.server.common.model.user.IUserLoginModel;
import com.cuprum.server.common.utils.EntityTestDAO;
import com.cuprum.web.common.client.exceptions.model.user.InvalidPasswordException;
import com.cuprum.web.common.client.exceptions.model.user.UserNotFoundException;

public class UserLoginTest extends EntityTestDAO {
	/**
	 * Logger for this class. TODO: tests for session date during login
	 */
	static final Logger LOGGER = Logger.getLogger(UserLoginTest.class);

	private final static String LOGIN = "testdLogin";

	private final static String PASSWORD = "password";

	private final static String MAIL = "mail@mail.com";
/*-
	@Test
	public void LoginTest() throws UserNotFoundException,
			InvalidPasswordException {
		User user1 = new User();
		user1.setLogin(LOGIN);
		user1.setPassword(PASSWORD);
		user1.setMail(MAIL);
		getBean().save(user1);

		restart();

		IUserLoginModel login = getDAO().getBean(IUserLoginModel.class);

		UserSession session1 = login.login(LOGIN, PASSWORD);

		LOGGER.debug(session1.getSession());

		assertEquals(user1.getId(), session1.getUser().getId());
		assertFalse(user1 == session1.getUser());
	}

	@Test(expected = InvalidPasswordException.class)
	public void LoginTestInvalidPassword() throws UserNotFoundException,
			InvalidPasswordException {
		User user1 = new User();
		user1.setLogin(LOGIN);
		user1.setPassword(PASSWORD + "1");
		user1.setMail(MAIL);

		getBean().save(user1);

		restart();

		IUserLoginModel login = getDAO().getBean(IUserLoginModel.class);
		login.login(LOGIN, PASSWORD);
	}

	@Test(expected = UserNotFoundException.class)
	public void LoginTestUserNotFound() throws UserNotFoundException,
			InvalidPasswordException {
		User user1 = new User();
		user1.setLogin(LOGIN + "1");
		user1.setPassword(PASSWORD);
		user1.setMail(MAIL);

		getBean().save(user1);

		restart();

		IUserLoginModel login = getDAO().getBean(IUserLoginModel.class);
		login.login(LOGIN, PASSWORD);
	}

	@Test
	public void LogoutTest1() throws UserNotFoundException,
			InvalidPasswordException {
		User user1 = new User();
		user1.setLogin(LOGIN);
		user1.setPassword(PASSWORD);
		user1.setMail(MAIL);

		getBean().save(user1);

		restart();

		IUserLoginModel login = getDAO().getBean(IUserLoginModel.class);
		UserSession session1 = login.login(LOGIN, PASSWORD);

		LOGGER.debug(session1.getSession());

		assertEquals(user1.getId(), session1.getUser().getId());
		assertFalse(user1 == session1.getUser());

		Long sessionId = session1.getId();

		login.logout(session1);

		restart();

		assertNull(getBean().get(UserSession.class, sessionId));
	}

	public void LogoutTest2() throws UserNotFoundException,
			InvalidPasswordException {
		User user1 = new User();
		user1.setLogin(LOGIN);
		user1.setPassword(PASSWORD);
		user1.setMail(MAIL);

		getBean().save(user1);

		restart();

		IUserLoginModel login = getDAO().getBean(IUserLoginModel.class);

		UserSession session1 = login.login(LOGIN, PASSWORD);

		LOGGER.debug(session1.getSession());

		assertEquals(user1.getId(), session1.getUser().getId());
		assertFalse(user1 == session1.getUser());

		String sessionKey = session1.getSession();

		login.logout(session1);

		restart();

		List list = getBean().createQuery(
				"from Session where session='" + sessionKey + "'");
		assertEquals(0, list.size());
	}

	@Test
	public void LogoutExists1() throws UserNotFoundException,
			InvalidPasswordException {
		User user1 = new User();
		user1.setLogin(LOGIN);

		IUserLoginModel login = getDAO().getBean(IUserLoginModel.class);
		assertFalse(login.existsLogin(LOGIN));
	}

	@Test
	public void LogoutExists2() throws UserNotFoundException,
			InvalidPasswordException {
		User user1 = new User();
		user1.setLogin(LOGIN);
		user1.setPassword(PASSWORD);
		user1.setMail(MAIL);

		getBean().save(user1);

		restart();

		User user2 = new User();
		user2.setLogin(LOGIN);

		IUserLoginModel login = getDAO().getBean(IUserLoginModel.class);
		assertTrue(login.existsLogin(LOGIN));
	}
	-*/
}
