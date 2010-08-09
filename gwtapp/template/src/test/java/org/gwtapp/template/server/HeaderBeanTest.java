package org.gwtapp.template.server;

import java.io.IOException;

import org.gwtapp.template.client.Template;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.xml.sax.SAXException;

import com.meterware.httpunit.PostMethodWebRequest;
import com.meterware.httpunit.WebRequest;
import com.meterware.httpunit.WebResponse;
import com.meterware.servletunit.ServletRunner;
import com.meterware.servletunit.ServletUnitClient;

public class HeaderBeanTest {

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

	private String getTag(WebResponse response) {
		return response.getHeaderField(Template.Header.TAG);
	}

	private String getStyle(WebResponse response) {
		return response.getHeaderField(Template.Header.STYLE);
	}

	private String getStyleclass(WebResponse response) {
		return response.getHeaderField(Template.Header.STYLE_CLASS);
	}
}
