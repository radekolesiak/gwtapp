package org.gwtapp.template.client;

import java.util.HashMap;
import java.util.Map;

import org.gwtapp.template.client.callback.TFieldCallback;
import org.gwtapp.template.client.handler.WidgetHandler;
import org.gwtapp.template.client.ui.TemplatePanel;
import org.junit.Test;

import com.google.gwt.user.client.ui.RootPanel;

public class GwtTestMessages extends TemplateTest {

	private class MessagePanel extends TemplatePanel<Void> {

		private final WidgetHandler handler = new WidgetHandler();

		public MessagePanel(final String html) {
			super(new TemplateCallback() {

				private final TFieldCallback callback = new TFieldCallback();

				@Override
				public Template getTemplate() {
					return new Template("div", "", "", html);
				}

				@Override
				public void template(TemplatePanel<?> owner,
						Map<String, TemplateHandler> widgetHandlers) {
					callback.template(owner, widgetHandlers);
				}
			});
			add("test", handler);
		}

		public TemplateMessage getTemplateMessage() {
			return handler.getTemplateMessage();
		}
	}

	@Test
	public void testTemplateHeaderInitState() {
		assertEquals("Template-Tag", Template.Header.TAG);
		assertEquals("Template-Style", Template.Header.STYLE);
		assertEquals("Template-Style-Class", Template.Header.STYLE_CLASS);
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

	/*-
	 @Test
	 public void testParseLineSeparator() {
	 Map<String, String> map = new HashMap<String, String>();
	 TemplateUtils.parseMessages("a-b:x;ab:c;a-b-c:y;", map);
	 for (String key : map.keySet()) {
	 System.out.println(key);
	 }
	 assertTrue(map.containsKey("ab"));
	 assertTrue(map.containsKey("a-b"));
	 assertTrue(map.containsKey("a-b-c"));
	 assertEquals("c", map.get("ab"));
	 assertEquals("x", map.get("a-b"));
	 assertEquals("y", map.get("a-b-c"));
	 }

	 @Test
	 public void testLineSeparator() {
	 MessagePanel mp = new MessagePanel(
	 "<div t:field=\"test\" t:msg=\"a-b:x;ab:c;a-b-c:y;\"></div>") {
	 @Override
	 public void onAddWidgets() {
	 System.out.println();
	 TemplateMessage tm = getTemplateMessage();
	 assertNotNull(tm);
	 assertNotNull(tm.getPattern("ab"));
	 assertNotNull(tm.getPattern("a-b"));
	 assertNotNull(tm.getPattern("a-b-c"));
	 assertEquals("c", tm.getMessage("ab", new String[0]));
	 assertEquals("c", tm.getMessage("ab", new Param[0]));
	 assertEquals("x", tm.getMessage("a-b", new String[0]));
	 assertEquals("x", tm.getMessage("a-b", new Param[0]));
	 assertEquals("y", tm.getMessage("a-b-c", new String[0]));
	 assertEquals("y", tm.getMessage("a-b-c", new Param[0]));
	 }
	 };
	 RootPanel.get().add(mp);
	 }
	 */

	@Test
	public void testDoubleTemplateMessage() {
		testTemplateMessage();
		testTemplateMessage();
	}
}
