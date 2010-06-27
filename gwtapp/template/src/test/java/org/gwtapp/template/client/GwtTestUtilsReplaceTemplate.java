package org.gwtapp.template.client;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

public class GwtTestUtilsReplaceTemplate extends TemplateTest {

	@Test
	public void testReplaceTemplate1a() {
		Map<String, String> ids = new HashMap<String, String>();
		ids.put("login", "gwt-1");
		assertEquals("<div id=\"gwt-1\"></div>", TemplateUtils.replaceTemplate(
				"t:field", "<div t:field=\"login\"></div>", ids));
	}

	@Test
	public void testReplaceTemplate1b() {
		Map<String, String> ids = new HashMap<String, String>();
		ids.put("login", "gwt-1");
		assertEquals(
				"<div id=\"gwt-1\"></div><div id=\"gwt-1\"></div>",
				TemplateUtils
						.replaceTemplate(
								"t:field",
								"<div t:field=\"login\"></div><div t:field=\"login\"></div>",
								ids));
	}

	@Test
	public void testReplaceTemplate2a() {
		Map<String, String> ids = new HashMap<String, String>();
		ids.put("login", "gwt-1");
		assertEquals("<div id=\"gwt-1\"></div>", TemplateUtils.replaceTemplate(
				"tfield", "<div tfield=\"login\"></div>", ids));
	}

	@Test
	public void testReplaceTemplate2b() {
		Map<String, String> ids = new HashMap<String, String>();
		ids.put("login", "gwt-1");
		assertEquals(
				"<div id=\"gwt-1\"></div><div id=\"gwt-1\"></div>",
				TemplateUtils
						.replaceTemplate(
								"tfield",
								"<div tfield=\"login\"></div><div tfield=\"login\"></div>",
								ids));
	}

}
