package org.gwtapp.startapp;

import junit.framework.Test;
import junit.framework.TestSuite;

import com.google.gwt.junit.tools.GWTTestSuite;

public class GwtTestSuiteStartApp extends GWTTestSuite {
	public static Test suite() {
		TestSuite suite = new TestSuite("Tests for a StartApp Application");
		// suite.addTestSuite(GwtTestTemplateUtils.class);
		return suite;
	}
}
