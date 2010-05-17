package org.gwtapp.template.client;

import java.util.HashMap;
import java.util.Map;

import org.gwtapp.template.client.handlers.WidgetHandler;
import org.gwtapp.template.client.ui.TemplatePanel;
import org.junit.Test;

import com.google.gwt.user.client.ui.RootPanel;

public class GwtTestMessages extends TemplateTest {

	private class MessagePanel extends TemplatePanel<Void> {

		private final WidgetHandler handler = new WidgetHandler();

		public MessagePanel(String html) {
			super("div", html);
			addWidgetHandler("test", handler);
		}

		public TemplateMessage getTemplateMessage() {
			return handler.getTemplateMessage();
		}
	}

	@Test
	public void testMessagesParse() {
		Map<String, String> map = new HashMap<String, String>();
		TemplateUtils.parseMessages("ab:xyz;cd:012;ef:;", map);
		assertTrue(map.containsKey("ab"));
		assertTrue(map.containsKey("cd"));
		assertFalse(map.containsKey("ef"));
		assertEquals("xyz", map.get("ab"));
		assertEquals("012", map.get("cd"));
	}

	@Test
	public void testTemplatePattern() {
		MessagePanel mp = new MessagePanel(
				"<div t:field=\"test\" t:msg=\"ab:xyz; cd:012;ef:;\"></div>") {
			@Override
			public void onAddWidgets() {
				TemplateMessage tm = getTemplateMessage();
				assertNotNull(tm);
				assertNotNull(tm.getPattern("ab"));
				assertNotNull(tm.getPattern("cd"));
				assertNull(tm.getPattern("ef"));
				assertEquals("xyz", tm.getPattern("ab"));
				assertEquals("012", tm.getPattern("cd"));
			}
		};
		RootPanel.get().add(mp);
	}

	@Test
	public void testTemplateMessage() {
		MessagePanel mp = new MessagePanel(
				"<div t:field=\"test\" t:msg=\"ab:x{1}y{2}z; cd:0{1}1{2}2;ef:;\"></div>") {
			@Override
			public void onAddWidgets() {
				TemplateMessage tm = getTemplateMessage();
				assertNotNull(tm);
				assertNotNull(tm.getPattern("ab"));
				assertNotNull(tm.getPattern("cd"));
				assertNull(tm.getPattern("ef"));
				assertEquals("xAyBz", tm.getMessage("ab", "A", "B"));
				assertEquals("0A1B2", tm.getMessage("cd", "A", "B"));
			}
		};
		RootPanel.get().add(mp);
	}

	@Test
	public void testDoubleTemplateMessage() {
		testTemplateMessage();
		testTemplateMessage();
	}
}
