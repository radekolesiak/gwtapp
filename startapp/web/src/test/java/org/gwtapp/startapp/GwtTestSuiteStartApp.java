package org.gwtapp.startapp;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import com.google.gwt.junit.tools.GWTTestSuite;

public class GwtTestSuiteStartApp extends GWTTestSuite {

	public static Test suite() throws ClassNotFoundException {
		TestSuite suite = new TestSuite("Tests for a StartApp Application");
		addTest(suite, "org.gwtapp.core.client.ui.GwtTestDelegatedPanel");
		addTest(suite, "org.gwtapp.template.client.GwtTestReplaceParameters");
		addTest(suite, "org.gwtapp.template.client.GwtTestUtilsReplaceTemplate");
		addTest(suite, "org.gwtapp.template.client.GwtTestMessages");
		addTest(suite,
				"org.gwtapp.template.client.GwtTestDOMTemplateRepository");
		addTest(suite, "org.gwtapp.template.client.GwtTestWidgetWrapper");
		addTest(suite, "org.gwtapp.template.client.GwtTestBooleanWrapper");
		addTest(suite, "org.gwtapp.template.client.GwtTestTemplatePanel");
		addTest(suite, "org.gwtapp.template.client.GwtTestTemplateFormPanel");
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
