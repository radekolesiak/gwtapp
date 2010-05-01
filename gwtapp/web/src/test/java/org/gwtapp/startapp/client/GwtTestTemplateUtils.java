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
	public void testReplaceParameters1() {
		assertEquals("AXB", TemplateUtils.replaceParameters("A{1}B", "X"));
	}

	@Test
	public void testReplaceParameters2() {
		assertEquals("A{0}B", TemplateUtils.replaceParameters("A{0}B", "X"));
	}

	@Test
	public void testReplaceParameters3() {
		assertEquals("A{-1}B", TemplateUtils.replaceParameters("A{-1}B", "X"));
	}

	@Test
	public void testReplaceParameter4() {
		assertEquals("AYBXC", TemplateUtils.replaceParameters("A{2}B{1}C", "X",
				"Y"));
	}

	@Test
	public void testReplaceParameter5() {
		assertEquals("AYBXC", TemplateUtils.replaceParameters("A{11}B{10}C",
				"a", "b", "c", "d", "e", "f", "g", "h", "i", "X", "Y"));
	}
}
