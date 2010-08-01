package org.gwtapp.startapp.server.rest;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.gwtapp.rest.RestEngine;
import org.gwtapp.rest.RestEngineImpl;
import org.gwtapp.startapp.server.StartAppServiceImpl;

import com.google.inject.Singleton;

@Singleton
public class StartAppRestServiceImpl extends HttpServlet {

	private static final long serialVersionUID = -596924399486878203L;

	private final RestEngine rest = new RestEngineImpl();
	private final StartAppServiceImpl service = new StartAppServiceImpl();

	public StartAppRestServiceImpl() {
		rest.setRemoteService(service);
	}

	@Override
	protected void service(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String[] args = (String[]) request.getParameterValues("arg");
		String[] types = (String[]) request.getParameterValues("type");
		String result = rest.process(getMethodName(request), args, types);
		response.getWriter().print(result);
	}

	public String getMethodName(HttpServletRequest request) {
		return request.getParameter("method");
	}
}
