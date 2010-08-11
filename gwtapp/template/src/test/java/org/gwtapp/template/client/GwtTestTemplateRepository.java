package org.gwtapp.template.client;

import org.gwtapp.template.client.ui.TemplatePanel.TemplateCallback;
import org.junit.Test;

public class GwtTestTemplateRepository extends TemplateTest {

	@Test
	public void testInitState() {
		TemplateRepository repository = new TemplateRepository();
		assertEquals("/", repository.getRepository());
		assertEquals(TemplateRepository.Type.TFIELD, repository
				.getDefaultType());
	}
	

	@Test
	public void testGetEmptyTemplate() {
		TemplateRepository repository = new TemplateRepository();
		TemplateCallback callback = repository.load("test");
		assertNotNull(callback);
		assertNotNull(callback.getTemplate());
		assertEquals("div", callback.getTemplate().getTag());
		assertEquals("", callback.getTemplate().getHtml());
		assertEquals("", callback.getTemplate().getStyle());
		assertEquals("", callback.getTemplate().getStyleClass());
	}	
	
	@Test
	public void testGetNotEmptyTemplate() {
		addTemplateRepository();
		TemplateRepository repository = new TemplateRepository("/templates/");
		TemplateCallback callback = repository.load("test");
		assertNotNull(callback);
		assertNotNull(callback.getTemplate());
		assertEquals("b", callback.getTemplate().getTag());
		assertEquals("Hello test!", callback.getTemplate().getHtml());
		assertEquals("color:#336633;", callback.getTemplate().getStyle());
		assertEquals("template-test", callback.getTemplate().getStyleClass());
	}
	
	private native void addTemplateRepository()/*-{
		$wnd.Templates = new Array();
		$wnd.Templates["/templates/"] = new Array();
		$wnd.Templates["/templates/"]["test"] = new Array();
		$wnd.Templates["/templates/"]["test"]["tag"] = "b";
		$wnd.Templates["/templates/"]["test"]["style"] = "color:#336633;";
		$wnd.Templates["/templates/"]["test"]["styleclass"] = "template-test";
		$wnd.Templates["/templates/"]["test"]["body"] = "Hello test!";
	}-*/;
}