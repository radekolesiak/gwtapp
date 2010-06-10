package org.gwtapp.startapp.server.data;

import org.gwtapp.startapp.rpc.data.user.register.UserRegister;
import org.gwtapp.startapp.rpc.data.user.register.UserRegisterModel;
import org.gwtapp.startapp.rpc.data.user.register.UserRegisterModelImpl;
import org.junit.Assert;
import org.junit.Test;

public class MetaFieldsTest {

	private final String email = "emailGin";

	@Test
	public void BeanTest() {
		UserRegister ur = new UserRegisterModelImpl();
		Assert.assertNotNull(ur.getLogin());
		Assert.assertNull(ur.getPassword());
		Assert.assertNull(ur.getEmail());
		ur.setEmail(email);
		Assert.assertNotNull(ur.getEmail());
		Assert.assertEquals(email, ur.getEmail());
	}

	@Test
	public void ModelDataTest() {
		UserRegisterModel ur = new UserRegisterModelImpl();
		Assert.assertNotNull(ur.getLogin());
		Assert.assertNull(ur.getPassword());
		Assert.assertNull(ur.getEmail());
		ur.setEmail(email);
		Assert.assertNotNull(ur.get(UserRegister.EMAIL.name()));
		Assert.assertEquals(email, ur.get(UserRegister.EMAIL.name()));
	}
}
