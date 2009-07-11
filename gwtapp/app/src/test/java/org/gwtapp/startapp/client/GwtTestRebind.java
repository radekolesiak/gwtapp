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

	public void testGenerator() {
		String textA ="aaa";
		String textB ="bbb";
		String login ="login";
		
		TestModel testModel = GWT.create(TestModel.class);
		testModel.setText(textA);
		assertEquals(textA, testModel.getText());
		assertEquals(textA, testModel.get(TestModel.TEXT));
		testModel.set(TestModel.TEXT, textB);
		assertEquals(textB, testModel.getText());
		assertEquals(textB, testModel.get(TestModel.TEXT));
		
		UserRegister userRegister = GWT.create(UserRegister.class);
		userRegister.setLogin(login);
		assertEquals(login, userRegister.getLogin());
	}
}
