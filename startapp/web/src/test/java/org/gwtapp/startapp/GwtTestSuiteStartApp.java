package org.gwtapp.startapp;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import com.google.gwt.junit.tools.GWTTestSuite;

public class GwtTestSuiteStartApp extends GWTTestSuite {

	public static Test suite() throws ClassNotFoundException {
		TestSuite suite = new TestSuite("Tests for a StartApp Application");
		addTest(suite, "org.gwtapp.core.client.ui.GwtTestDelegatedPanel");
		addTest(suite, "org.gwtapp.core.client.di.GwtTestAsyncCallbackInjector");
		addTest(suite, "org.gwtapp.template.client.GwtTestReplaceParameters");
		addTest(suite, "org.gwtapp.template.client.GwtTestUtilsReplaceTemplate");
		addTest(suite, "org.gwtapp.template.client.GwtTestMessages");
		addTest(suite, "org.gwtapp.template.client.GwtTestTemplateRepository");
		addTest(suite, "org.gwtapp.template.client.GwtTestHtmlRepository");
		addTest(suite, "org.gwtapp.template.client.ui.GwtTestWidgetWrapper");
		addTest(suite, "org.gwtapp.template.client.ui.GwtTestBooleanWrapper");
		addTest(suite, "org.gwtapp.template.client.ui.GwtTestTemplatePanel");
		addTest(suite, "org.gwtapp.template.client.ui.GwtTestTemplateFormPanel");
		addTest(suite, "org.gwtapp.template.client.ui.GwtTestWrapperValueCache");
		addTest(suite, "org.gwtapp.template.client.callback.GwtTestTFieldCallback");
		addTest(suite, "org.gwtapp.form.client.ui.GwtTestTemplateModelPanel");
		addTest(suite, "org.gwtapp.io.client.GwtTestIOClient");		
		addTest(suite, "org.gwtapp.extension.user.client.test.GwtTestUser");
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
