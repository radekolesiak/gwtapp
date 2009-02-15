package com.cuprum.server.common;

import org.hibernate.exception.ConstraintViolationException;
import org.junit.Test;
import org.springframework.dao.DataIntegrityViolationException;

import com.cuprum.server.common.entities.User;
import com.cuprum.server.common.utils.EntityTestDAO;

public class ModelTest extends EntityTestDAO {
	private final static String LOGIN = "testdLogin";

	private final static String PASSWORD = "password";

	private final static String MAIL = "mail@mail.com";

	@Test
	public void doubleSaveOrIgnore() {
		User user1 = new User();
		user1.setLogin(LOGIN + "1");
		user1.setPassword(PASSWORD);
		user1.setMail(MAIL);
		getBean().saveOrIgnore(user1);
		getBean().saveOrIgnore(user1);
	}

	@Test(expected = ConstraintViolationException.class)
	public void doubleSave() throws Throwable {
		User user1 = new User();
		user1.setLogin(LOGIN + "2");
		user1.setPassword(PASSWORD);
		user1.setMail(MAIL);
		getBean().save(user1);
		try {
			getBean().save(user1);
		} catch (DataIntegrityViolationException e) {
			throw e.getCause();
		}
	}
}
