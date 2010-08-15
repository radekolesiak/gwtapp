package org.gwtapp.ccalc.client;

import org.gwtapp.ccalc.client.data.user.User;
import org.gwtapp.core.client.SimpleAsyncCallback;
import org.junit.Test;

public class GwtTestUser extends CCalcTest {

	@Test
	public void testUserService() {
		CCalc.service.getUser("aba", new SimpleAsyncCallback<User>() {
			@Override
			public void onSuccess(User user) {
				assertNotNull(user);
				assertEquals(new Long(1L),user.getId());
				assertEquals("aba",user.getLogin());
				assertEquals("aba@email.com",user.getEmail());
				assertEquals("Name: aba",user.getName());
				finishTest();
			}
		});
		delayTestFinish(250);
	}
}
