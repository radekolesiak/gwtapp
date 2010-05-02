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
				"<div t:field=\"login\"></div>", ids));
	}

	@Test
	public void testReplaceTemplate1b() {
		Map<String, String> ids = new HashMap<String, String>();
		ids.put("login", "gwt-1");
		assertEquals(
				"<div id=\"gwt-1\"></div><div id=\"gwt-1\"></div>",
				TemplateUtils
						.replaceTemplate(
								"<div t:field=\"login\"></div><div t:field=\"login\"></div>",
								ids));
	}

}
