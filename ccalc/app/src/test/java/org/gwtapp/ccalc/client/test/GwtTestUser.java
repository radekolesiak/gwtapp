package org.gwtapp.ccalc.client.test;

import org.gwtapp.ccalc.client.CCalc;
import org.gwtapp.ccalc.client.CCalcTest;
import org.gwtapp.ccalc.client.data.user.User;
import org.gwtapp.ccalc.client.data.user.exception.UserValidationException;
import org.gwtapp.core.client.SimpleAsyncCallback;
import org.gwtapp.core.rpc.exception.RpcException;
import org.junit.Test;

public class GwtTestUser extends CCalcTest {

	@Test
	public void testUserService() {
		CCalc.service.getUser("aba", new SimpleAsyncCallback<User>() {
			@Override
			public void onSuccess(User user) {
				assertNotNull(user);
				assertEquals(new Long(1L), user.getId());
				assertEquals("aba", user.getLogin());
				assertEquals("aba@email.com", user.getEmail());
				assertEquals("Name: aba", user.getName());
				finishTest();
			}
		});
		delayTestFinish(250);
	}

	@Test
	public void testUserServiceNullResult() {
		CCalc.service.getUser("xyz", new SimpleAsyncCallback<User>() {
			@Override
			public void onSuccess(User user) {
				assertNull(user);
				finishTest();
			}
		});
		delayTestFinish(250);
	}

	@Test
	public void testUserServiceRpcException() {
		CCalc.service.getUser(null, new SimpleAsyncCallback<User>() {
			@Override
			public void onFailure(Throwable e) {
				assertTrue(e instanceof RpcException);
				finishTest();
			}
		});
		delayTestFinish(250);
	}

	@Test
	public void testUserDB() {
		CCalc.service.getUser("012", new SimpleAsyncCallback<User>() {
			@Override
			public void onSuccess(User user) {
				assertNotNull(user);
				assertEquals(new Long(1L), user.getId());
				assertEquals("012", user.getLogin());
				assertEquals("012@012.com", user.getEmail());
				assertEquals("Zero One Two", user.getName());
				finishTest();
			}
		});
		delayTestFinish(250);
	}

	@Test
	public void testUserServiceNotFound() {
		CCalc.service.getUser("qwerty", new SimpleAsyncCallback<User>() {
			@Override
			public void onFailure(Throwable e) {
				assertTrue(e instanceof RpcException);
				finishTest();
			}
		});
		delayTestFinish(250);
	}

	@Test
	public void testAddUserAlreadyExistsABAInvalidEmail() {
		CCalc.service.addUser(new User("aba", "", ""),
				new SimpleAsyncCallback<Long>() {
					@Override
					public void onFailure(Throwable e) {
						assertTrue(e instanceof UserValidationException);
						UserValidationException validation = (UserValidationException) e;
						assertEquals(
								UserValidationException.Login.ALREADY_EXISTS,
								validation.getLogin());
						assertEquals(UserValidationException.Email.INVALID,
								validation.getEmail());
						finishTest();
					}
				});
		delayTestFinish(250);
	}

	@Test
	public void testAddUserAlreadyExistsXYZInvalidEmail() {
		CCalc.service.addUser(new User("xyz", "", ""),
				new SimpleAsyncCallback<Long>() {
					@Override
					public void onFailure(Throwable e) {
						assertTrue(e instanceof UserValidationException);
						UserValidationException validation = (UserValidationException) e;
						assertEquals(
								UserValidationException.Login.ALREADY_EXISTS,
								validation.getLogin());
						assertEquals(UserValidationException.Email.INVALID,
								validation.getEmail());
						finishTest();
					}
				});
		delayTestFinish(250);
	}
}
