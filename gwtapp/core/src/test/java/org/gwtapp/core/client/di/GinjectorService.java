package org.gwtapp.core.client.di;

import com.google.gwt.inject.client.GinModules;
import com.google.gwt.inject.client.Ginjector;

@GinModules(ClientModule.class)
public interface GinjectorService extends Ginjector {
	SomeClass getSomeClass();
}
