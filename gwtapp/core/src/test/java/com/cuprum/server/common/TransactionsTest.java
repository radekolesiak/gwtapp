package com.cuprum.server.common;

import org.apache.log4j.Logger;
import org.junit.Test;

import com.cuprum.server.common.entities.User;
import com.cuprum.server.common.model.IOnDuplicateUpdate;
import com.cuprum.server.common.model.user.xql.CrLogin;
import com.cuprum.server.common.utils.EntityTestDAO;

//@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
//@Transactional
//@RunWith(SpringJUnit4ClassRunner.class)
public class TransactionsTest extends EntityTestDAO {
	private final static Logger LOGGER = Logger
			.getLogger(TransactionsTest.class);

	private final static String LOGIN = "testdLogin";

	private final static String PASSWORD = "password";

	private final static String MAIL = "mail@mail.com";

	class T extends Thread {
		public void run() {
			action();
		}
	}

	@Test
	public void concurrency() throws InterruptedException {
		T t1 = new T();
		T t2 = new T();
		t1.start();
		t2.start();
		Thread.sleep(2000);
	}

	public void action() {
		getBean().saveOnDuplicateUpdate(User.class,
				new IOnDuplicateUpdate<User>() {
					public User find() {
						return (User) getBean().execute(new CrLogin(LOGIN));
					}

					public User update(User object) {
						object.setMail(MAIL + "#");
						return object;
					}

					public User create() {
						User user = new User();
						user.setLogin(LOGIN);
						user.setPassword(PASSWORD);
						user.setMail(MAIL);
						return user;
					}
				});
	}
}
