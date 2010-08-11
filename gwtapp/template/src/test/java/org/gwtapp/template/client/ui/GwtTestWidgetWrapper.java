package org.gwtapp.template.client.ui;

import org.gwtapp.template.client.TemplateTest;
import org.gwtapp.template.client.ui.WidgetWrapper;
import org.junit.Test;

import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.NativeEvent;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.DomEvent;
import com.google.gwt.user.client.ui.RootPanel;

public class GwtTestWidgetWrapper extends TemplateTest {

	private final static String STYLE_DISABLED = "disabled";

	@Test
	public void testInitState() {
		WidgetWrapper wrapper = new WidgetWrapper();
		assertFalse(wrapper.isAttached());
		assertTrue(wrapper.isEnabled());
		assertEquals("div", wrapper.getElement().getTagName().toLowerCase());
		assertEquals("", wrapper.getHTML());
		assertEquals("", wrapper.getText());
		assertFalse(wrapper.getStyleName().toLowerCase().contains(
				STYLE_DISABLED));
	}

	@Test
	public void testEnabledStyleName() {
		WidgetWrapper wrapper = new WidgetWrapper();
		assertFalse(wrapper.isAttached());
		assertTrue(wrapper.isEnabled());
		assertFalse(wrapper.getStyleName().toLowerCase().contains(
				STYLE_DISABLED));
		wrapper.setEnabled(false);
		assertFalse(wrapper.isEnabled());
		assertTrue(wrapper.getStyleName().toLowerCase()
				.contains(STYLE_DISABLED));
	}

	@Test
	public void testSetHTML() {
		WidgetWrapper wrapper = new WidgetWrapper();
		assertEquals("", wrapper.getHTML());
		wrapper.setHTML("a &amp; b & c");
		assertEquals("a &amp; b &amp; c", wrapper.getHTML());
		assertEquals("a & b & c", wrapper.getText());
	}

	@Test
	public void testSetText() {
		WidgetWrapper wrapper = new WidgetWrapper();
		assertEquals("", wrapper.getText());
		wrapper.setText("a &amp; b & c");
		assertEquals("a &amp;amp; b &amp; c", wrapper.getHTML());
		assertEquals("a &amp; b & c", wrapper.getText());
	}

	@Test
	public void testClickHandler() {
		class Handled {
			public boolean handled = false;
		}
		WidgetWrapper wrapper = new WidgetWrapper();
		RootPanel.get().add(wrapper);
		final Handled handled = new Handled();
		wrapper.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				handled.handled = true;
			}
		});
		NativeEvent event = Document.get().createClickEvent(0, 0, 0, 0, 0,
				false, false, false, false);
		DomEvent.fireNativeEvent(event, wrapper);
		assertTrue(handled.handled);
	}
}
