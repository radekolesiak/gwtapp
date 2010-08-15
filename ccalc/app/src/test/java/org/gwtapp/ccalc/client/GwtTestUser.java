package org.gwtapp.ccalc.client;

import org.gwtapp.ccalc.client.data.user.User;
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
}
