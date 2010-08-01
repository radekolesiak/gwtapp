package org.gwtapp.startapp.server.rest;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.servlet.GuiceServletContextListener;
import com.google.inject.servlet.ServletModule;

public class RestServletConfig extends GuiceServletContextListener {

	@Override
	protected Injector getInjector() {
		return Guice.createInjector(new ServletModule(){
			@Override
			protected void configureServlets() {
				System.out.println("GUICE");
				serve("/rest/startapp").with(StartAppRestServiceImpl.class);
			}			
		});
	}
}