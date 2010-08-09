package org.gwtapp.template.server;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.Assert;

public class HeaderBeanServlet extends HttpServlet {

	private static final long serialVersionUID = 1894774265326440188L;

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/plain");
		writeSelectMessage(request.getParameter("color"), response.getWriter());
		setColor(request, request.getParameter("color"));

		HeaderBean header = new HeaderBean();
		header.setResponse(response);

		String test = request.getParameter("test");
		if ("empty".equalsIgnoreCase(test)) {
			testEmpty(header);
		}
	}

	void writeSelectMessage(String color, PrintWriter pw) throws IOException {
		pw.print("You selected " + color);
		pw.close();
	}

	void setColor(HttpServletRequest req, String color) throws ServletException {
		req.getSession().setAttribute("color", color);
	}

	private void testEmpty(HeaderBean header) {
	}
}