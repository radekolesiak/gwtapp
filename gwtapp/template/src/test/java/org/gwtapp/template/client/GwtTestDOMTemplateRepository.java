package org.gwtapp.template.client;

import org.junit.Test;

import com.google.gwt.dom.client.Element;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.RootPanel;

public class GwtTestDOMTemplateRepository extends TemplateTest {

	@Test
	public void testGetEmptyTemplate() {
		HtmlRepository repository = new HtmlRepository();
		Element template = repository.load("t");
		assertNotNull(template);
		assertEquals("DIV", template.getTagName());
		assertEquals("", template.getInnerHTML());
		assertEquals("", template.getAttribute("style"));
		assertEquals("", template.getClassName());
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
		Element template = repository.load(id);
		assertNotNull(template);
		assertEquals(tag, template.getTagName());
		assertEquals(html, template.getInnerHTML());
		assertEquals(style, template.getAttribute("style"));
		assertEquals(styleClass, template.getClassName());
	}
}
