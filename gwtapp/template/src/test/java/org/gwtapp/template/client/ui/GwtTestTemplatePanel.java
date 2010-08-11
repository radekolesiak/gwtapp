package org.gwtapp.template.client.ui;

import java.util.Map;

import org.gwtapp.template.client.Template;
import org.gwtapp.template.client.TemplateHandler;
import org.gwtapp.template.client.TemplateTest;
import org.gwtapp.template.client.Value;
import org.gwtapp.template.client.ui.TemplatePanel;
import org.junit.Test;

import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;

public class GwtTestTemplatePanel extends TemplateTest {

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

	@Test
	public void testInitState() {
		TemplatePanel<Void> panel = new TemplatePanel<Void>(
				new CustomTemplateCallback());
		assertFalse(panel.isAttached());
		assertFalse(panel.isTemplated());
		assertTrue(panel.isVisible());
		assertEquals("div", panel.getElement().getTagName().toLowerCase());
		assertEquals("template-panel", panel.getStyleName());
		assertEquals("", panel.getElement().getInnerHTML());
		assertNull(panel.getName());
		assertFalse(panel.isInitValueByDeferredCommand());
	}

	@Test
	public void testInitStateByTemplate() {
		TemplatePanel<Void> panel = new TemplatePanel<Void>(
				new CustomTemplateCallback(new Template("span", "aa", "bb",
						"xyz")));
		assertFalse(panel.isAttached());
		assertFalse(panel.isTemplated());
		assertTrue(panel.isVisible());
		assertEquals("span", panel.getElement().getTagName().toLowerCase());
		assertEquals("aa", panel.getElement().getAttribute("style"));
		assertEquals("template-panel bb", panel.getStyleName());
		assertEquals("xyz", panel.getElement().getInnerHTML());
		assertNull(panel.getName());
		assertFalse(panel.isInitValueByDeferredCommand());
	}

	@Test
	public void testNameAttribute() {
		TemplatePanel<Void> p1 = new TemplatePanel<Void>(
				new CustomTemplateCallback());
		TemplatePanel<Void> p2 = new TemplatePanel<Void>(
				new CustomTemplateCallback(new Template("span", "aa", "bb",
						"xyz")));
		assertNull(p1.getName());
		assertNull(p2.getName());
		p1.setName("abc");
		p2.setName("xyz");
		assertEquals("abc", p1.getName());
		assertEquals("xyz", p2.getName());
	}

	@Test
	public void testValueChangeHandler() {
		TemplatePanel<String> panel = new TemplatePanel<String>(
				new CustomTemplateCallback());
		RootPanel.get().add(panel);
		assertTrue(panel.isAttached());
		final Value<Boolean> handled = new Value<Boolean>(false);
		final Value<String> value = new Value<String>();
		assertFalse(handled.get());
		panel.addValueChangeHandler(new ValueChangeHandler<String>() {
			@Override
			public void onValueChange(ValueChangeEvent<String> event) {
				handled.set(true);
				value.set(event.getValue());
			}
		});
		assertNull(panel.getValue());
		panel.setValue("012", true);
		assertTrue(handled.get());
		assertEquals(value.get(), panel.getValue());
		assertEquals("012", panel.getValue());
	}

	@Test
	public void testFireChangeHandler() {
		TemplatePanel<String> panel = new TemplatePanel<String>(
				new CustomTemplateCallback());
		RootPanel.get().add(panel);
		final Value<Boolean> handled = new Value<Boolean>(false);
		panel.addChangeHandler(new ChangeHandler() {
			@Override
			public void onChange(ChangeEvent event) {
				handled.set(true);
			}
		});
		panel.fireChangeEvent();
		assertTrue(handled.get());
	}

	@Test
	public void testIsInitValueByDeferredCommand() {
		TemplatePanel<String> panel = new TemplatePanel<String>(
				new CustomTemplateCallback());
		assertFalse(panel.isInitValueByDeferredCommand());
		panel.setInitValueByDeferredCommand(true);
		assertTrue(panel.isInitValueByDeferredCommand());
		panel.setInitValueByDeferredCommand(false);
		assertFalse(panel.isInitValueByDeferredCommand());
	}

	@Test
	public void testAddWidget() {
		TemplatePanel<String> panel = new TemplatePanel<String>(
				new CustomTemplateCallback());
		FlowPanel widget = new FlowPanel();
		assertFalse(widget.isAttached());
		panel.add("w", widget);
		assertFalse(widget.isAttached());
		RootPanel.get().add(panel);
		assertTrue(widget.isAttached());
	}

	@Test
	public void testAddWidgetEmptyName() {
		TemplatePanel<String> panel = new TemplatePanel<String>(
				new CustomTemplateCallback());
		FlowPanel widget = new FlowPanel();
		assertFalse(widget.isAttached());
		try {
			panel.add("", widget);
			assertFalse(true);
		} catch (AssertionError e) {
		}
	}

	@Test
	public void testAddWidgetNullName() {
		TemplatePanel<String> panel = new TemplatePanel<String>(
				new CustomTemplateCallback());
		FlowPanel widget = new FlowPanel();
		assertFalse(widget.isAttached());
		try {
			panel.add(null, widget);
			assertFalse(true);
		} catch (AssertionError e) {
		}
	}

	@Test
	public void testAddHandler() {
		TemplatePanel<String> panel = new TemplatePanel<String>(
				new CustomTemplateCallback());
		final FlowPanel widget = new FlowPanel();
		assertFalse(widget.isAttached());
		panel.add("w", new TemplateHandler() {
			@Override
			public Widget onWidget(String id) {
				return widget;
			}
		});
		assertFalse(widget.isAttached());
		RootPanel.get().add(panel);
		assertTrue(widget.isAttached());
	}
}
