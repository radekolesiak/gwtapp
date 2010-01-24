package org.gwtapp.core.server.template;

import javax.servlet.http.HttpServletRequest;

public class Template {
	public static String add(HttpServletRequest request, String name) {
		return "template=\"" + name + "-" + request.getParameter("uid") + "\"";
	}
}
