package org.gwtapp.template.server;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class StatefulServlet extends HttpServlet {

	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		resp.setContentType("text/plain");
		writeSelectMessage(req.getParameter("color"), resp.getWriter());
		setColor(req, req.getParameter("color"));
	}

	void writeSelectMessage(String color, PrintWriter pw) throws IOException {
		pw.print("You selected " + color);
		pw.close();
	}

	void setColor(HttpServletRequest req, String color) throws ServletException {
		req.getSession().setAttribute("color", color);
	}
}