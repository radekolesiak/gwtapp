package org.gwtapp.template.client.callback;

import java.util.Map;

import org.gwtapp.template.client.Template;
import org.gwtapp.template.client.TemplateTest;
import org.gwtapp.template.client.handler.TemplateHandler;
import org.gwtapp.template.client.ui.TemplatePanel;
import org.junit.Test;

import com.google.gwt.dom.client.Element;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;

public class GwtTestTFieldCallback extends TemplateTest {

	@Test
	public void testInitState() {
		TFieldCallback callback = new TFieldCallback();
		assertEquals("t:field", callback.getPattern());
	}

	@Test
	public void testChangePattern() {
		TFieldCallback callback = new TFieldCallback();
		assertEquals("t:field", callback.getPattern());
		callback.setPattern("xyz");
		assertEquals("xyz", callback.getPattern());
	}

	@Test
	public void testTemplateByHTML() {
		String html = "<div t:field=\"a\"></div><div t:field=\"b\"></div>";
		Template template = new Template("span", "tstyle", "tclass", html);
		TemplatePanel<Void> panel = new TemplatePanel<Void>(
				loadTemplateCallback(template, new TFieldCallback()));
		TextBox a = new TextBox();
		TextBox b = new TextBox();
		panel.add("a", a);
		panel.add("b", b);
		assertFalse(a.isAttached());
		assertFalse(b.isAttached());
		RootPanel.get().add(panel);
		assertTrue(a.isAttached());
		assertTrue(b.isAttached());
	}

	@Test
	public void testTemplateByElementInnerHTML() {
		String html = "<div id=\"e\"><div t:field=\"a\"></div><div t:field=\"b\"></div></div>";
		RootPanel.get().getElement().setInnerHTML(html);
		TFieldCallback callback = new TFieldCallback();
		TemplatePanel<Void> panel = new TemplatePanel<Void>(
				loadElementCallback("e", callback));
		TextBox a = new TextBox();
		TextBox b = new TextBox();
		panel.add("a", a);
		panel.add("b", b);
		assertFalse(a.isAttached());
		assertFalse(b.isAttached());
		assertTrue(DOM.getElementById("e").getInnerHTML().toLowerCase().contains("t:field"));
		assertNotNull(loadElementCallback("e", callback).getElement());
		panel.attach();
		assertTrue(a.isAttached());
		assertTrue(b.isAttached());
		assertFalse(DOM.getElementById("e").getInnerHTML().toLowerCase().contains("t:field"));
	}

	private TemplatePanel.TemplateCallback loadTemplateCallback(
			final Template template, final TemplatePanel.Callback callback) {
		return new TemplatePanel.TemplateCallback() {
			@Override
			public Template getTemplate() {
				return template;
			}

			@Override
			public void template(TemplatePanel<?> owner,
					Map<String, TemplateHandler> widgetHandlers) {
				callback.template(owner, widgetHandlers);
			}
		};
	}

	private TemplatePanel.ElementCallback loadElementCallback(final String id,
			final TemplatePanel.Callback callback) {
		return new TemplatePanel.ElementCallback() {
			@Override
			public Element getElement() {
				return DOM.getElementById(id);
			}

			@Override
			public void template(TemplatePanel<?> owner,
					Map<String, TemplateHandler> widgetHandlers) {
				callback.template(owner, widgetHandlers);
			}
		};
	}
}
