package org.gwtapp.template.client.ui;

import org.gwtapp.template.client.TemplateTest;
import org.junit.Test;

public class GwtTestWrapperValueCache extends TemplateTest {

	private final static int n = 100;

	@Test
	public void testBooleanWrapperCheckBox() {
		BooleanWrapper wrapper = new BooleanWrapper(true);
		long t0 = System.currentTimeMillis();
		boolean value = true;
		wrapper.setValue(value);
		for (int i = 0; i < n; i++) {
			assertEquals(new Boolean(value), wrapper.getValue());
			wrapper.setValue(value);
			assertEquals(new Boolean(value), wrapper.getValue());
		}
		long t1 = System.currentTimeMillis();
		for (int i = 0; i < n; i++) {
			assertEquals(new Boolean(value), wrapper.getValue());
			value = !value;
			wrapper.setValue(value);
			assertEquals(new Boolean(value), wrapper.getValue());
		}
		long t2 = System.currentTimeMillis();
		assertTrue((t1 - t0) < (t2 - t1) / 2);
	}

	@Test
	public void testBooleanWrapperRadioButton() {
		BooleanWrapper wrapper = new BooleanWrapper(false);
		long t0 = System.currentTimeMillis();
		boolean value = true;
		wrapper.setValue(value);
		for (int i = 0; i < n; i++) {
			assertEquals(new Boolean(value), wrapper.getValue());
			wrapper.setValue(value);
			assertEquals(new Boolean(value), wrapper.getValue());
		}
		long t1 = System.currentTimeMillis();
		for (int i = 0; i < n; i++) {
			assertEquals(new Boolean(value), wrapper.getValue());
			value = !value;
			wrapper.setValue(value);
			assertEquals(new Boolean(value), wrapper.getValue());
		}
		long t2 = System.currentTimeMillis();
		assertTrue((t1 - t0) < (t2 - t1) / 2);
	}

	@Test
	public void testTextBoxWrapper() {
		TextBoxWrapper wrapper = new TextBoxWrapper();
		long t0 = System.currentTimeMillis();
		String value = "xyz";
		String[] c = { "abc", value };
		wrapper.setValue(value);
		for (int i = 0; i < n; i++) {
			assertEquals(value, wrapper.getValue());
			wrapper.setValue(value);
			assertEquals(value, wrapper.getValue());
		}
		long t1 = System.currentTimeMillis();
		for (int i = 0; i < n; i++) {
			assertEquals(value, wrapper.getValue());
			value = c[i % 2];
			wrapper.setValue(value);
			assertEquals(value, wrapper.getValue());
		}
		long t2 = System.currentTimeMillis();
		assertTrue((t1 - t0) < (t2 - t1) / 2);
	}

	@Test
	public void testTextAreaWrapper() {
		TextAreaWrapper wrapper = new TextAreaWrapper();
		long t0 = System.currentTimeMillis();
		String value = "xyz";
		String[] c = { "abc", value };
		wrapper.setValue(value);
		for (int i = 0; i < n; i++) {
			assertEquals(value, wrapper.getValue());
			wrapper.setValue(value);
			assertEquals(value, wrapper.getValue());
		}
		long t1 = System.currentTimeMillis();
		for (int i = 0; i < n; i++) {
			assertEquals(value, wrapper.getValue());
			value = c[i % 2];
			wrapper.setValue(value);
			assertEquals(value, wrapper.getValue());
		}
		long t2 = System.currentTimeMillis();
		assertTrue((t1 - t0) < (t2 - t1) / 2);
	}
}
