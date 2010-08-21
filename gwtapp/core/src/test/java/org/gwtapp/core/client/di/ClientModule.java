package org.gwtapp.core.client.di;

import org.gwtapp.core.client.AsyncCallbackInjector;

import com.google.gwt.inject.client.AbstractGinModule;

public class ClientModule extends AbstractGinModule {
	@Override
	protected void configure() {
		bind(AsyncCallbackInjector.class)
				.to(AsyncCallbackInjectorAdapter.class);
	}

}
