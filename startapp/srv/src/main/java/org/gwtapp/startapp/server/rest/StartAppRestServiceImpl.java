package org.gwtapp.startapp.server.rest;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.gwtapp.rest.RestEngineImpl;
import org.gwtapp.startapp.server.StartAppServiceImpl;

import com.google.inject.Singleton;

@Singleton
public class StartAppRestServiceImpl extends HttpServlet {

	private static final long serialVersionUID = -596924399486878203L;

	private final RestEngineImpl rest = new RestEngineImpl();
	private final StartAppServiceImpl service = new StartAppServiceImpl();

	public StartAppRestServiceImpl() {
		rest.setRemoteService(service);
	}

	@Override
	protected void service(HttpServletRequest arg0, HttpServletResponse arg1)
			throws ServletException, IOException {
	}
}
