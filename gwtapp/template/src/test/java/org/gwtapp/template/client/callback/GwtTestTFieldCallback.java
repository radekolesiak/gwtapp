package org.gwtapp.template.client.callback;

import org.gwtapp.template.client.TemplateTest;
import org.junit.Test;

public class GwtTestTFieldCallback extends TemplateTest {

	@Test
	public void testInitState() {
		TFieldCallback callback = new TFieldCallback();
		assertEquals("t:field", callback.getPattern());
	}

	@Test
	public void testChangePattern() {
		TFieldCallback callback = new TFieldCallback();
		assertEquals("t:field", callback.getPattern());
		callback.setPattern("xyz");
		assertEquals("xyz", callback.getPattern());
	}

}
