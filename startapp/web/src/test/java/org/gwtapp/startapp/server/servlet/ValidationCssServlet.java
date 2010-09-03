package org.gwtapp.startapp.server.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.gwtapp.core.server.ValidationCssBean;
import org.gwtapp.extension.user.client.data.exception.UserValidationException;

import com.google.inject.Singleton;

@SuppressWarnings("serial")
@Singleton
public class ValidationCssServlet extends HttpServlet {
	@Override
	protected void service(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		ValidationCssBean generator = new ValidationCssBean();
		generator.setValidator(UserValidationException.class);
		IOUtils.write(generator.getCSS(), response.getOutputStream());
	}
}
