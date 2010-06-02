package org.gwtapp.template.client;

import org.junit.Test;

import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.RootPanel;

public class GwtTestDOMTemplateRepository extends TemplateTest {

	@Test
	public void testGetEmptyTemplate() {
		DOMTemplateRepository repository = new DOMTemplateRepository();
		Template template = repository.load("t");
		assertNotNull(template);
		assertEquals("div", template.getTag());
		assertEquals("", template.getHtml());
		assertEquals("", template.getStyle());
		assertEquals("", template.getStyleClass());
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
		DOMTemplateRepository repository = new DOMTemplateRepository();
		Template template = repository.load(id);
		assertNotNull(template);
		assertEquals(tag, template.getTag());
		assertEquals(html, template.getHtml());
		assertEquals(style, template.getStyle());
		assertEquals(styleClass, template.getStyleClass());
	}
}
