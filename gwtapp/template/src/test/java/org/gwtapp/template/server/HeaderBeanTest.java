package org.gwtapp.template.server;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.Assert;
import org.junit.Test;
import org.xml.sax.SAXException;

import com.meterware.httpunit.PostMethodWebRequest;
import com.meterware.httpunit.WebRequest;
import com.meterware.httpunit.WebResponse;
import com.meterware.servletunit.ServletRunner;
import com.meterware.servletunit.ServletUnitClient;

public class HeaderBeanTest {

	@Test
	public void testHeaderBean() throws IOException, SAXException {
		ServletRunner runner = new ServletRunner();
		ServletUnitClient client = runner.newClient();
		runner.registerServlet("myServlet", StatefulServlet.class.getName());
		WebRequest request = new PostMethodWebRequest(
				"http://test.meterware.com/myServlet");
		request.setParameter("color", "red");
		WebResponse response = client.getResponse(request);
		Assert.assertNotNull("No response received", response);
		Assert.assertEquals("content type", "text/plain", response
				.getContentType());
		Assert.assertEquals("requested resource", "You selected red", response
				.getText());

		// HttpServletResponse response=new HttpServletResponse();
		HeaderBean header = new HeaderBean();
		// Assert.assertNull(header.)
	}
}
