package org.gwtapp.startapp.client;

import java.util.ArrayList;
import java.util.List;

import org.gwtapp.startapp.client.data.TestModel;
import org.gwtapp.startapp.client.data.UserRegister;
import org.gwtapp.startapp.client.data.UserRegisterModel;
import org.junit.Test;

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

		UserRegister userRegister = GWT.create(UserRegisterModel.class);
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

	public void testGeneratorGenerics1() {
		TestModel testModel = GWT.create(TestModel.class);
		List<Double> doubles = new ArrayList<Double>();
		doubles.add(3.0);
		testModel.set(TestModel.DOUBLES, doubles);
		doubles = testModel.getDoubles();
		assertNotNull(doubles);
		assertEquals(1, doubles.size());
		assertTrue(Math.abs(3.0 - doubles.get(0)) < 1e-3);
	}

	/*-
	public void testGeneratorGenerics2() {
		TestModel testModel = GWT.create(TestModel.class);
		List<Integer> integers = new ArrayList<Integer>();
		integers.add(3);
		try {
			testModel.set(TestModel.DOUBLES, integers);
			assertTrue(false);
		} catch (Exception e) {
		}
		List<Double> doubles = testModel.getDoubles();
		assertNotNull(integers);
		assertEquals(1, integers.size());
		assertTrue(Math.abs(3.0 - doubles.get(0)) < 1e-3);
	}*/
	

	@Test
	public void bindingAnnotationTest1() {
		UserRegister userRegister = GWT.create(UserRegister.class);
		assertNotNull(userRegister.getTockens());
		assertTrue(userRegister.getTockens() instanceof ArrayList);
	}
	
}
