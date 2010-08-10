package org.gwtapp.template.client;

import java.util.Map;

import org.gwtapp.template.client.ui.TemplatePanel;
import org.junit.Test;

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
		assertEquals("templatePanel", panel.getStyleName());
		assertEquals("", panel.getElement().getInnerHTML());
		assertNull(panel.getName());
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
		assertEquals("templatePanel bb", panel.getStyleName());
		assertEquals("xyz", panel.getElement().getInnerHTML());
		assertNull(panel.getName());
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
}
