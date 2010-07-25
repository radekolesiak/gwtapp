package org.gwtapp.template.client;

import org.gwtapp.template.client.ui.TemplatePanel.ElementCallback;
import org.junit.Test;

import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.RootPanel;

public class GwtTestDOMTemplateRepository extends TemplateTest {

	@Test
	public void testGetEmptyTemplate() {
		HtmlRepository repository = new HtmlRepository();
		ElementCallback callback = repository.load("t");
		assertNotNull(callback);
		assertNotNull(callback.getElement());
		assertEquals("DIV", callback.getElement().getTagName());
		assertEquals("", callback.getElement().getInnerHTML());
		assertEquals("", callback.getElement().getAttribute("style"));
		assertEquals("", callback.getElement().getClassName());
	}

	@Test
	public void testGetNotEmptyTemplate() {
		String id = "t";
		String tag = "B";
		String style = "width:300px;";
		String styleClass = "sc";
		String html = "<div id=\"b\"></div>";
		RootPanel.get().add(
				new HTMLPanel("<" + tag + " id=\"" + id + "\" style=\"" + style
						+ "\" class=\"" + styleClass + "\">" + html + "</"
						+ tag + ">"));
		HtmlRepository repository = new HtmlRepository();
		ElementCallback callback = repository.load(id);
		assertNotNull(callback);
		assertNotNull(callback.getElement());
		assertEquals(tag, callback.getElement().getTagName());
		assertEquals(html, callback.getElement().getInnerHTML());
		assertEquals(style, callback.getElement().getAttribute("style"));
		assertEquals(styleClass, callback.getElement().getClassName());
	}
}
