package org.gwtapp.template.server;

import org.junit.Test;

public class HeaderBeanTest {

	@Test
	public void emptyTest() {
	}

	/*-
	private final static String URL = "http://gwtapp.org/myServlet";

	private ServletUnitClient client = null;
	private WebRequest request = null;

	@Before
	public void setup() {
		ServletRunner runner = new ServletRunner();
		runner.registerServlet("myServlet", HeaderBeanServlet.class.getName());
		client = runner.newClient();
		request = new PostMethodWebRequest(URL);
	}

	@After
	public void done() {
		client = null;
		request = null;
	}

	@Test
	public void testHeaderBeanServlet() throws IOException, SAXException {
		request.setParameter("color", "red");
		WebResponse response = client.getResponse(request);
		Assert.assertNotNull("No response received", response);
		Assert.assertEquals("content type", "text/plain", response
				.getContentType());
		Assert.assertEquals("requested resource", "You selected red", response
				.getText());
	}

	@Test
	public void testHeaderBeanEmpty() throws IOException, SAXException {
		WebResponse response = client.getResponse(request);
		Assert.assertEquals("", getTag(response));
		Assert.assertEquals("", getStyle(response));
		Assert.assertEquals("", getStyleclass(response));
	}

	@Test
	public void testHeaderBeanTagDiv() throws IOException, SAXException {
		request.setParameter(Template.Header.TAG, "div");
		WebResponse response = client.getResponse(request);
		Assert.assertEquals("div", getTag(response));
		Assert.assertEquals("", getStyle(response));
		Assert.assertEquals("", getStyleclass(response));
	}

	@Test
	public void testHeaderBeanStyleABC() throws IOException, SAXException {
		request.setParameter(Template.Header.STYLE, "abc");
		WebResponse response = client.getResponse(request);
		Assert.assertEquals("", getTag(response));
		Assert.assertEquals("abc", getStyle(response));
		Assert.assertEquals("", getStyleclass(response));
	}

	@Test
	public void testHeaderBeanStyleclassXYZ() throws IOException, SAXException {
		request.setParameter(Template.Header.STYLE_CLASS, "xyz");
		WebResponse response = client.getResponse(request);
		Assert.assertEquals("", getTag(response));
		Assert.assertEquals("", getStyle(response));
		Assert.assertEquals("xyz", getStyleclass(response));
	}

	@Test
	public void testHeaderBeanFull() throws IOException, SAXException {
		request.setParameter(Template.Header.TAG, "div");
		request.setParameter(Template.Header.STYLE, "abc");
		request.setParameter(Template.Header.STYLE_CLASS, "xyz");
		WebResponse response = client.getResponse(request);
		Assert.assertEquals("div", getTag(response));
		Assert.assertEquals("abc", getStyle(response));
		Assert.assertEquals("xyz", getStyleclass(response));
	}

	private String getTag(WebResponse response) {
		return response.getHeaderField(Template.Header.TAG);
	}

	private String getStyle(WebResponse response) {
		return response.getHeaderField(Template.Header.STYLE);
	}

	private String getStyleclass(WebResponse response) {
		return response.getHeaderField(Template.Header.STYLE_CLASS);
	}
	 */
}
