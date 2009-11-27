package org.gwtapp.startapp.server.data;

import org.gwtapp.startapp.client.data.UserRegister;
import org.gwtapp.startapp.client.data.UserRegisterModel;
import org.gwtapp.startapp.client.data.UserRegisterModelImpl;
import org.junit.Assert;
import org.junit.Test;

public class AutoFieldsTest {

	private final String login = "loGin";

	@Test
	public void BeanTest() {
		UserRegister ur = new UserRegisterModelImpl();
		Assert.assertNull(ur.getLogin());
		Assert.assertNull(ur.getPassword());
		Assert.assertNull(ur.getEmail());
		Assert.assertNotNull(ur.getTockens());
		ur.setLogin(login);
		Assert.assertNotNull(ur.getLogin());
		Assert.assertEquals(login, ur.getLogin());
	}

	@Test
	public void ModelDataTest() {
		UserRegisterModel ur = new UserRegisterModelImpl();
		Assert.assertNull(ur.getLogin());
		Assert.assertNull(ur.getPassword());
		Assert.assertNull(ur.getEmail());
		Assert.assertNotNull(ur.getTockens());
		ur.setLogin(login);
		Assert.assertNotNull(ur.get(UserRegister.LOGIN));
		Assert.assertEquals(login, ur.get(UserRegister.LOGIN));
	}
}
