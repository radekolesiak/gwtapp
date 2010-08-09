package org.gwtapp.template.server;

import java.io.IOException;

import org.gwtapp.template.client.Template;
import org.junit.Assert;
import org.junit.Test;
import org.xml.sax.SAXException;

import com.meterware.httpunit.PostMethodWebRequest;
import com.meterware.httpunit.WebRequest;
import com.meterware.httpunit.WebResponse;
import com.meterware.servletunit.ServletRunner;
import com.meterware.servletunit.ServletUnitClient;

public class HeaderBeanTest {

	private final static String URL = "http://gwtapp.org/myServlet";

	@Test
	public void testHeaderBeanServlet() throws IOException, SAXException {
		ServletRunner runner = new ServletRunner();
		ServletUnitClient client = runner.newClient();
		runner.registerServlet("myServlet", HeaderBeanServlet.class.getName());
		WebRequest request = new PostMethodWebRequest(URL);
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
		ServletRunner runner = new ServletRunner();
		ServletUnitClient client = runner.newClient();
		runner.registerServlet("myServlet", HeaderBeanServlet.class.getName());
		WebRequest request = new PostMethodWebRequest(URL);
		WebResponse response = client.getResponse(request);

		request.setParameter("test", "empty");
		Assert.assertNull(response.getHeaderField(Template.Header.TAG));
		Assert.assertNull(response.getHeaderField(Template.Header.STYLE));
		Assert.assertNull(response.getHeaderField(Template.Header.STYLE_CLASS));
	}
}
