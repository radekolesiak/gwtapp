package org.gwtapp.core.client.di;

import org.gwtapp.core.client.AsyncCallbackInjector;

import com.google.inject.Inject;

public class SomeClass {

	@Inject
	private AsyncCallbackInjector injector;

	public SomeClass() {
	}

	AsyncCallbackInjector getInjector() {
		return injector;
	}
}
