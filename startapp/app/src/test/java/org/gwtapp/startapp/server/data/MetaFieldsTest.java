package org.gwtapp.startapp.server.data;

import org.gwtapp.startapp.rpc.data.user.register.UserRegister;
import org.gwtapp.startapp.rpc.data.user.register.UserRegisterModel;
import org.gwtapp.startapp.rpc.data.user.register.UserRegisterModelImpl;
import org.junit.Assert;
import org.junit.Test;

public class MetaFieldsTest {

	private final String login = "loGin";

	@Test
	public void BeanTest() {
		UserRegister ur = new UserRegisterModelImpl();
		Assert.assertNull(ur.getLogin());
		Assert.assertNull(ur.getPassword());
		Assert.assertNull(ur.getEmail());
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
		ur.setLogin(login);
		Assert.assertNotNull(ur.get(UserRegister.LOGIN.name()));
		Assert.assertEquals(login, ur.get(UserRegister.LOGIN.name()));
	}
}
