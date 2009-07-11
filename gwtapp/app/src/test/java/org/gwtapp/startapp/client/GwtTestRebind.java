package org.gwtapp.startapp.client;

import org.gwtapp.startapp.client.data.TestModel;
import org.gwtapp.startapp.client.data.UserRegister;

import com.google.gwt.core.client.GWT;
import com.google.gwt.junit.client.GWTTestCase;

public class GwtTestRebind extends GWTTestCase {

	@Override
	public String getModuleName() {
		return "org.gwtapp.startapp.StartApp";
	}

	public void testGenerator1() {
		String textA = "aaa";
		String textB = "bbb";
		String login = "login";
		Integer number = 3;

		TestModel testModel = GWT.create(TestModel.class);
		testModel.setText(textA);
		assertEquals(textA, testModel.getText());
		assertEquals(textA, testModel.get(TestModel.TEXT));
		testModel.set(TestModel.TEXT, textB);
		assertEquals(textB, testModel.getText());
		assertEquals(textB, testModel.get(TestModel.TEXT));

		testModel.set(TestModel.NUMBER, number);
		assertEquals(number, testModel.getNumber());
		assertEquals(number, testModel.get(TestModel.NUMBER));

		UserRegister userRegister = GWT.create(UserRegister.class);
		userRegister.setLogin(login);
		assertEquals(login, userRegister.getLogin());
	}

	public void testGenerator2() throws IllegalArgumentException {
		TestModel testModel = GWT.create(TestModel.class);
		try {
			testModel.get("abc" + TestModel.TEXT);
			assertTrue(false);
		} catch (IllegalArgumentException e) {
		}
	}
}
