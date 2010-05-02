package org.gwtapp.startapp;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import com.google.gwt.junit.tools.GWTTestSuite;

public class GwtTestSuiteStartApp extends GWTTestSuite {
	
	public static Test suite() throws ClassNotFoundException {
		TestSuite suite = new TestSuite("Tests for a StartApp Application");
		addTest(suite, "org.gwtapp.template.client.GwtTestReplaceParameters");
		addTest(suite, "org.gwtapp.template.client.GwtTestUtilsReplaceTemplate");
		return suite;
	}

	@SuppressWarnings("unchecked")
	private static void addTest(TestSuite suite, String c)
			throws ClassNotFoundException {
		Class<? extends TestCase> C = (Class<? extends TestCase>) Class
				.forName(c);
		suite.addTestSuite(C);
	}
}
