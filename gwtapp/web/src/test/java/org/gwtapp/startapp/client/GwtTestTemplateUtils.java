package org.gwtapp.startapp.client;

import org.gwtapp.template.client.TemplateUtils;
import org.junit.Test;

import com.google.gwt.junit.client.GWTTestCase;

public class GwtTestTemplateUtils extends GWTTestCase {

	@Override
	public String getModuleName() {
		return "org.gwtapp.startapp.StartAppEntryPoint";
	}

	@Test
	public void testReplaceParameters() {
		assertEquals("AXB", TemplateUtils.replaceParameters("A{1}B", "X"));
	}
}
