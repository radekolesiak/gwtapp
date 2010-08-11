package org.gwtapp.template.client.ui;

import java.util.Map;

import org.gwtapp.template.client.Template;
import org.gwtapp.template.client.TemplateHandler;
import org.gwtapp.template.client.TemplateTest;
import org.gwtapp.template.client.Value;
import org.gwtapp.template.client.ui.TemplateFormPanel;
import org.gwtapp.template.client.ui.TemplatePanel;
import org.junit.Test;

import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;

public class GwtTestTemplateFormPanel extends TemplateTest {

	private class CustomTemplateCallback implements
			TemplatePanel.TemplateCallback {

		private final Template template;

		public CustomTemplateCallback() {
			this(new Template());
		}

		public CustomTemplateCallback(Template template) {
			this.template = template;
		}

		@Override
		public Template getTemplate() {
			return template;
		}

		@Override
		public void template(TemplatePanel<?> owner,
				Map<String, TemplateHandler> widgetHandlers) {
			for (TemplateHandler handler : widgetHandlers.values()) {
				owner.add(handler.onWidget(null));
			}
		}
	}

	private class TemplateFormPanelTest<T> extends TemplateFormPanel<T> {
		public TemplateFormPanelTest(TemplateCallback callback) {
			super(callback);
		}

		@SuppressWarnings("unchecked")
		@Override
		public Map getFields() {
			return super.getFields();
		}
	}

	@Test
	public void testInitState() {
		TemplateFormPanelTest<Void> panel = new TemplateFormPanelTest<Void>(
				new CustomTemplateCallback());
		assertEquals("template-panel template-form-panel", panel.getStyleName());
		assertNotNull(panel.getFields());
	}

	@Test
	public void testAddFieldWidgetEmptyName() {
		TemplateFormPanelTest<Void> panel = new TemplateFormPanelTest<Void>(
				new CustomTemplateCallback());
		TextBox widget = new TextBox();
		assertEquals("", widget.getName());
		try {
			panel.addField(widget);
			assertFalse(true);
		} catch (AssertionError e) {
		}
		try {
			panel.addField("", widget);
			assertFalse(true);
		} catch (AssertionError e) {
		}
	}

	@Test
	public void testAddFieldWidgetNullName() {
		TemplateFormPanelTest<Void> panel = new TemplateFormPanelTest<Void>(
				new CustomTemplateCallback());
		TextBox widget = new TextBox();
		assertEquals("", widget.getName());
		try {
			panel.addField(null, widget);
			assertFalse(true);
		} catch (AssertionError e) {
		}
	}

	@Test
	public void testAddFieldToCollection() {
		TemplateFormPanelTest<Void> panel = new TemplateFormPanelTest<Void>(
				new CustomTemplateCallback());
		TextBox a = new TextBox();
		TextBox b = new TextBox();
		TextBox c = new TextBox();
		panel.addField("a", a);
		panel.addField("b", b);
		panel.addField("c", c);
		assertNotNull(panel.getFields());
		assertEquals(3, panel.getFields().size());
		assertTrue(panel.getFields().containsKey("a"));
		assertTrue(panel.getFields().containsKey("b"));
		assertTrue(panel.getFields().containsKey("c"));
		assertEquals(a, panel.getFields().get("a"));
		assertEquals(b, panel.getFields().get("b"));
		assertEquals(c, panel.getFields().get("c"));
		assertEquals(a, panel.getField("a"));
		assertEquals(b, panel.getField("b"));
		assertEquals(c, panel.getField("c"));
	}

	@Test
	public void testAddFieldHandlerToCollection() {
		TemplateFormPanelTest<Void> panel = new TemplateFormPanelTest<Void>(
				new CustomTemplateCallback());
		TextBox a = new TextBox();
		TextBox b = new TextBox();
		TextBox c = new TextBox();
		panel.addFieldHandler("a", a);
		panel.addFieldHandler("b", b);
		panel.addFieldHandler("c", c);
		assertNotNull(panel.getFields());
		assertEquals(3, panel.getFields().size());
		assertTrue(panel.getFields().containsKey("a"));
		assertTrue(panel.getFields().containsKey("b"));
		assertTrue(panel.getFields().containsKey("c"));
		assertEquals(a, panel.getFields().get("a"));
		assertEquals(b, panel.getFields().get("b"));
		assertEquals(c, panel.getFields().get("c"));
		assertEquals(a, panel.getField("a"));
		assertEquals(b, panel.getField("b"));
		assertEquals(c, panel.getField("c"));
	}

	@Test
	public void testAddFieldNotAttached() {
		TemplateFormPanelTest<Void> panel = new TemplateFormPanelTest<Void>(
				new CustomTemplateCallback());
		TextBox a = new TextBox();
		TextBox b = new TextBox();
		TextBox c = new TextBox();
		panel.addField("a", a);
		panel.addField("b", b);
		panel.addField("c", c);
		assertFalse(panel.isAttached());
		assertFalse(a.isAttached());
		assertFalse(b.isAttached());
		assertFalse(c.isAttached());
		RootPanel.get().add(panel);
		assertTrue(panel.isAttached());
		assertFalse(a.isAttached());
		assertFalse(b.isAttached());
		assertFalse(c.isAttached());
	}

	@Test
	public void testAddFieldAttached() {
		TemplateFormPanelTest<Void> panel = new TemplateFormPanelTest<Void>(
				new CustomTemplateCallback());
		TextBox a = new TextBox();
		TextBox b = new TextBox();
		TextBox c = new TextBox();
		panel.addFieldHandler("a", a);
		panel.addFieldHandler("b", b);
		panel.addFieldHandler("c", c);
		assertFalse(panel.isAttached());
		assertFalse(a.isAttached());
		assertFalse(b.isAttached());
		assertFalse(c.isAttached());
		RootPanel.get().add(panel);
		assertTrue(panel.isAttached());
		assertTrue(a.isAttached());
		assertTrue(b.isAttached());
		assertTrue(c.isAttached());
	}
	

	@Test
	public void testValueChangeHandler(){
		final Value<Boolean> handled = new Value<Boolean>(false);
		TemplateFormPanelTest<Void> panel = new TemplateFormPanelTest<Void>(
				new CustomTemplateCallback());
		RootPanel.get().add(panel);
		TextBox a = new TextBox();
		TextBox b = new TextBox();
		TextBox c = new TextBox();
		panel.addFieldHandler("a", a);
		panel.addFieldHandler("b", b);
		panel.addFieldHandler("c", c);
		panel.addValueChangeHandler(new ValueChangeHandler<Void>() {
			@Override
			public void onValueChange(ValueChangeEvent<Void> event) {
				handled.set(true);
			}
		});
		handled.set(false);
		a.setValue("test", true);
		assertTrue(handled.get());
		handled.set(false);
		b.setValue("test", true);
		assertTrue(handled.get());
		handled.set(false);
		c.setValue("test", true);
		assertTrue(handled.get());
	}
}
