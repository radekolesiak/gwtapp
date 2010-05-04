package org.gwtapp.template.client;

import java.util.HashMap;
import java.util.Map;

import org.gwtapp.template.client.ui.TemplatePanel;
import org.junit.Test;

public class GwtTestMessages extends TemplateTest {

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

	private class MessagePanel extends TemplatePanel<Void> {

		private final MessageWidgetHandler handler = new MessageWidgetHandler();

		public MessagePanel(String html) {
			super("div", html);
			addWidgetHandler("msg", handler);
		}

		public TemplateMessage getTemplateMessage() {
			return handler.getTemplateMessage();
		}
	}

	@Test
	public void testTemplateMessage() {
		new MessagePanel("<div t:msg=\"ab:xyz; cd:012;ef:;\"></div>") {
			@Override
			public void onAddWidgets() {
				TemplateMessage tm = getTemplateMessage();
				assertNotNull(tm);
				assertNotNull(tm.getMsg("ab"));
				assertNotNull(tm.getMsg("cd"));
				assertNull(tm.getMsg("ef"));
				assertEquals("xyz", tm.getMsg("ab"));
				assertEquals("012", tm.getMsg("cd"));
			}
		};
	}
}
