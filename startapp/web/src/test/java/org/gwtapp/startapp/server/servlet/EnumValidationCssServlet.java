package org.gwtapp.startapp.server.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.gwtapp.extension.user.client.data.exception.UserValidationException;
import org.gwtapp.validation.server.EnumValidationCssGenerator;

import com.google.inject.Singleton;

@SuppressWarnings("serial")
@Singleton
public class EnumValidationCssServlet extends HttpServlet {
	
	@Override
	protected void service(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		EnumValidationCssGenerator generator = new EnumValidationCssGenerator();
		generator.setValidationClass(UserValidationException.class);
		generator.setPrefix(getParam(request, "prefix"));
		generator.setSeparator(getParam(request, "separator"));
		generator.setStyle(getParam(request, "style"));
		IOUtils.write(generator.getCSS(), response.getOutputStream());
	}

	private String getParam(HttpServletRequest request, String name) {
		return StringUtils.defaultString(request.getParameter(name));
	}
}
