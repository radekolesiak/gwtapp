package org.gwtapp.template.client.ui;

import org.gwtapp.template.client.TemplateTest;
import org.gwtapp.template.client.Value;
import org.gwtapp.template.client.ui.BooleanWrapper;
import org.junit.Test;

import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.NativeEvent;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.DomEvent;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.ui.RootPanel;

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
	public void testInitStateRadioButton() {
		BooleanWrapper wrapper = new BooleanWrapper(false);
		assertFalse(wrapper.isAttached());
		assertTrue(wrapper.isEnabled());
		assertEquals("input", wrapper.getElement().getTagName().toLowerCase());
		assertEquals("radio", wrapper.getElement().getPropertyString("type"));
		assertEquals("", wrapper.getHTML());
		assertEquals("", wrapper.getText());
		assertFalse(wrapper.getStyleName().toLowerCase().contains(
				STYLE_DISABLED));
	}

	@Test
	public void testEnabledStyleNameCheckBox() {
		BooleanWrapper wrapper = new BooleanWrapper(true);
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
	public void testEnabledStyleNameRadioButton() {
		BooleanWrapper wrapper = new BooleanWrapper(false);
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
	public void testSetValueCheckBox() {
		BooleanWrapper wrapper = new BooleanWrapper(true);
		assertFalse(wrapper.getValue());
		wrapper.setValue(true);
		assertTrue(wrapper.getValue());
		wrapper.setValue(false);
		assertFalse(wrapper.getValue());
	}

	@Test
	public void testSetValueRadioButton() {
		BooleanWrapper wrapper = new BooleanWrapper(false);
		assertFalse(wrapper.getValue());
		wrapper.setValue(true);
		assertTrue(wrapper.getValue());
		wrapper.setValue(false);
		assertFalse(wrapper.getValue());
	}

	@Test
	public void testClickHandlerCheckBox() {
		class Handled {
			public boolean handled = false;
		}
		BooleanWrapper wrapper = new BooleanWrapper(true);
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

	@Test
	public void testClickHandlerRadioButton() {
		class Handled {
			public boolean handled = false;
		}
		BooleanWrapper wrapper = new BooleanWrapper(false);
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

	@Test
	public void testValueChangeHandlerCheckBox() {
		BooleanWrapper wrapper = new BooleanWrapper(true);
		RootPanel.get().add(wrapper);
		assertTrue(wrapper.isAttached());
		{
			final Value<Boolean> handled = new Value<Boolean>(false);
			final Value<Boolean> value = new Value<Boolean>();
			wrapper.addValueChangeHandler(new ValueChangeHandler<Boolean>() {
				@Override
				public void onValueChange(ValueChangeEvent<Boolean> event) {
					handled.set(true);
					value.set(event.getValue());
				}
			});
			assertFalse(wrapper.getValue());
			wrapper.setValue(true, true);
			assertEquals(value.get(), wrapper.getValue());
			assertTrue(wrapper.getValue());
		}
		{
			final Value<Boolean> handled = new Value<Boolean>(false);
			final Value<Boolean> value = new Value<Boolean>();
			wrapper.addValueChangeHandler(new ValueChangeHandler<Boolean>() {
				@Override
				public void onValueChange(ValueChangeEvent<Boolean> event) {
					handled.set(true);
					value.set(event.getValue());
				}
			});
			assertTrue(wrapper.getValue());
			wrapper.setValue(false, true);
			assertEquals(value.get(), wrapper.getValue());
			assertFalse(wrapper.getValue());
		}
	}

	@Test
	public void testValueChangeHandlerRadioButton() {
		BooleanWrapper wrapper = new BooleanWrapper(false);
		RootPanel.get().add(wrapper);
		assertTrue(wrapper.isAttached());
		{
			final Value<Boolean> handled = new Value<Boolean>(false);
			final Value<Boolean> value = new Value<Boolean>();
			wrapper.addValueChangeHandler(new ValueChangeHandler<Boolean>() {
				@Override
				public void onValueChange(ValueChangeEvent<Boolean> event) {
					handled.set(true);
					value.set(event.getValue());
				}
			});
			assertFalse(wrapper.getValue());
			wrapper.setValue(true, true);
			assertEquals(value.get(), wrapper.getValue());
			assertTrue(wrapper.getValue());
		}
		{
			final Value<Boolean> handled = new Value<Boolean>(false);
			final Value<Boolean> value = new Value<Boolean>();
			wrapper.addValueChangeHandler(new ValueChangeHandler<Boolean>() {
				@Override
				public void onValueChange(ValueChangeEvent<Boolean> event) {
					handled.set(true);
					value.set(event.getValue());
				}
			});
			assertTrue(wrapper.getValue());
			wrapper.setValue(false, true);
			assertEquals(value.get(), wrapper.getValue());
			assertFalse(wrapper.getValue());
		}
	}
}
