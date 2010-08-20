package org.gwtapp.startapp.client;

import com.google.gwt.inject.client.AbstractGinModule;

public class ClientModule extends AbstractGinModule {
	@Override
	protected void configure() {
		bind(SimpleService.class).to(SimpleServiceImpl.class);
	}
}
