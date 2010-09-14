package org.gwtapp.startapp;

import junit.framework.Test;
import junit.framework.TestSuite;

import org.gwtapp.core.client.di.GwtTestAsyncCallbackInjector;
import org.gwtapp.core.client.ui.GwtTestDelegatedPanel;
import org.gwtapp.extension.user.client.test.GwtTestUser;
import org.gwtapp.form.client.ui.GwtTestTemplateModelPanel;
import org.gwtapp.io.client.GwtTestIOClient;
import org.gwtapp.template.client.GwtTestHtmlRepository;
import org.gwtapp.template.client.GwtTestMessages;
import org.gwtapp.template.client.GwtTestReplaceParameters;
import org.gwtapp.template.client.GwtTestTemplateRepository;
import org.gwtapp.template.client.GwtTestUtilsReplaceTemplate;
import org.gwtapp.template.client.callback.GwtTestTFieldCallback;
import org.gwtapp.template.client.ui.GwtTestBooleanWrapper;
import org.gwtapp.template.client.ui.GwtTestTemplateFormPanel;
import org.gwtapp.template.client.ui.GwtTestTemplatePanel;
import org.gwtapp.template.client.ui.GwtTestWidgetWrapper;
import org.gwtapp.template.client.ui.GwtTestWrapperValueCache;

import com.google.gwt.junit.tools.GWTTestSuite;

public class GwtTestSuiteStartApp extends GWTTestSuite {
	public static Test suite() throws ClassNotFoundException {
		TestSuite suite = new TestSuite("Tests for GWTApp and StartApp applications");
		suite.addTestSuite(GwtTestUser.class);
		suite.addTestSuite(GwtTestDelegatedPanel.class);
		suite.addTestSuite(GwtTestAsyncCallbackInjector.class);
		suite.addTestSuite(GwtTestReplaceParameters.class);
		suite.addTestSuite(GwtTestUtilsReplaceTemplate.class);
		suite.addTestSuite(GwtTestMessages.class);
		suite.addTestSuite(GwtTestTemplateRepository.class);
		suite.addTestSuite(GwtTestHtmlRepository.class);
		suite.addTestSuite(GwtTestWidgetWrapper.class);
		suite.addTestSuite(GwtTestBooleanWrapper.class);
		suite.addTestSuite(GwtTestTemplatePanel.class);
		suite.addTestSuite(GwtTestTemplateFormPanel.class);
		suite.addTestSuite(GwtTestWrapperValueCache.class);
		suite.addTestSuite(GwtTestTFieldCallback.class);
		suite.addTestSuite(GwtTestTemplateModelPanel.class);
		suite.addTestSuite(GwtTestIOClient.class);
		return suite;
	}
}
