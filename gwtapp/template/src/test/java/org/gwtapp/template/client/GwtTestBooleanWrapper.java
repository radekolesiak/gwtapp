package org.gwtapp.template.client;

import org.gwtapp.template.client.ui.BooleanWrapper;
import org.junit.Test;

public class GwtTestBooleanWrapper extends TemplateTest {

	private final static String STYLE_DISABLED = "disabled";

	@Test
	public void testInitStateCheckBox() {
		BooleanWrapper wrapper = new BooleanWrapper(true);
		assertFalse(wrapper.isAttached());
		assertTrue(wrapper.isEnabled());
		assertEquals("input", wrapper.getElement().getTagName().toLowerCase());
		assertEquals("checkbox", wrapper.getElement().getPropertyString("type"));
		assertEquals("", wrapper.getHTML());
		assertEquals("", wrapper.getText());
		assertFalse(wrapper.getStyleName().toLowerCase().contains(
				STYLE_DISABLED));
	}

	@Test
	public void testInitStateradioButton() {
		BooleanWrapper wrapper = new BooleanWrapper(false);
		assertFalse(wrapper.isAttached());
		assertTrue(wrapper.isEnabled());
		assertEquals("input", wrapper.getElement().getTagName().toLowerCase());
		assertEquals("radiobutton", wrapper.getElement().getPropertyString("type"));
		assertEquals("", wrapper.getHTML());
		assertEquals("", wrapper.getText());
		assertFalse(wrapper.getStyleName().toLowerCase().contains(
				STYLE_DISABLED));
	}
}
