package org.gwtapp.core.rpc.data;

import com.google.inject.Provider;

public class ProviderAdapter<T> implements Provider<T> {

	private final T provider;

	public ProviderAdapter(T provider) {
		this.provider = provider;
	}

	@Override
	public T get() {
		return provider;
	}
}
