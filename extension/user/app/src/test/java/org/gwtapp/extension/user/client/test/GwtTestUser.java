package org.gwtapp.extension.user.client.test;

import org.gwtapp.core.client.SimpleAsyncCallback;
import org.gwtapp.core.rpc.exception.RpcException;
import org.gwtapp.extension.user.client.UserTestCase;
import org.gwtapp.extension.user.client.api.ReCaptchaUserService;
import org.gwtapp.extension.user.client.api.ReCaptchaUserServiceAsync;
import org.gwtapp.extension.user.client.api.UserService;
import org.gwtapp.extension.user.client.api.UserServiceAsync;
import org.gwtapp.extension.user.client.data.ReCaptchaUserImpl;
import org.gwtapp.extension.user.client.data.User;
import org.gwtapp.extension.user.client.data.exception.UserValidationException;
import org.junit.Test;

import com.google.gwt.core.client.GWT;

public class GwtTestUser extends UserTestCase {

	@Test
	public void testUserService() {
		UserServiceAsync user = GWT.create(UserService.class);
		user.getUser("aba", new SimpleAsyncCallback<User>() {
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
		UserServiceAsync user = GWT.create(UserService.class);
		user.getUser("xyz", new SimpleAsyncCallback<User>() {
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
		UserServiceAsync user = GWT.create(UserService.class);
		user.getUser(null, new SimpleAsyncCallback<User>() {
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
		UserServiceAsync user = GWT.create(UserService.class);
		user.getUser("012", new SimpleAsyncCallback<User>() {
			@Override
			public void onSuccess(User user) {
				assertNotNull(user);
				assertNotNull(user.getId());
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
		UserServiceAsync user = GWT.create(UserService.class);
		user.getUser("qwerty", new SimpleAsyncCallback<User>() {
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
		ReCaptchaUserServiceAsync user = GWT.create(ReCaptchaUserService.class);
		user.addUser(new ReCaptchaUserImpl("aba", "", ""),
				new SimpleAsyncCallback<Long>() {
					@Override
					public void onFailure(Throwable e) {
						assertTrue(e instanceof UserValidationException);
						UserValidationException validation = (UserValidationException) e;
						assertEquals(
								UserValidationException.Login.ALREADY_EXISTS,
								validation.get(UserValidationException.LOGIN)
										.get(0));
						assertEquals(UserValidationException.Email.EMPTY,
								validation.get(UserValidationException.EMAIL)
										.get(0));
						finishTest();
					}
				});
		delayTestFinish(250);
	}

	@Test
	public void testAddUserAlreadyExistsXYZInvalidEmail() {
		ReCaptchaUserServiceAsync user = GWT.create(ReCaptchaUserService.class);
		user.addUser(new ReCaptchaUserImpl("xyz", "", ""),
				new SimpleAsyncCallback<Long>() {
					@Override
					public void onFailure(Throwable e) {
						assertTrue(e instanceof UserValidationException);
						UserValidationException validation = (UserValidationException) e;
						assertEquals(
								UserValidationException.Login.ALREADY_EXISTS,
								validation.get(UserValidationException.LOGIN)
										.get(0));
						assertEquals(UserValidationException.Email.EMPTY,
								validation.get(UserValidationException.EMAIL)
										.get(0));
						finishTest();
					}
				});
		delayTestFinish(250);
	}
}
