package org.gwtapp.startapp.client;

import com.google.inject.Inject;

public class Adapters {
	
	private  SimpleService service;

	@Inject
	public Adapters(SimpleService service) {
		this.service = service;
	}

	public SimpleService getService() {
		return service;
	}
}
