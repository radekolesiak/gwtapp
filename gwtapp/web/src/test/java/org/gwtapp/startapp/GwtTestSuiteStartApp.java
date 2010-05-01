package org.gwtapp.startapp;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import com.google.gwt.junit.tools.GWTTestSuite;

public class GwtTestSuiteStartApp extends GWTTestSuite {
	@SuppressWarnings("unchecked")
	public static Test suite() throws ClassNotFoundException {
		TestSuite suite = new TestSuite("Tests for a StartApp Application");
		Class<? extends TestCase> c = (Class<? extends TestCase>) Class
				.forName("org.gwtapp.template.client.GwtTestTemplateUtils");
		suite.addTestSuite(c);
		return suite;
	}
}
