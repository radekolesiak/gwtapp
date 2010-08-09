package org.gwtapp.template.server;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.gwtapp.template.client.Template;

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
		header.setTag(request.getParameter(Template.Header.TAG));
		header.setStyle(request.getParameter(Template.Header.STYLE));
		header.setStyleclass(request.getParameter(Template.Header.STYLE_CLASS));
	}

	void writeSelectMessage(String color, PrintWriter pw) throws IOException {
		pw.print("You selected " + color);
		pw.close();
	}

	void setColor(HttpServletRequest req, String color) throws ServletException {
		req.getSession().setAttribute("color", color);
	}

}