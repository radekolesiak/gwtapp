package org.gwtapp.startapp.client;

import com.google.gwt.inject.client.GinModules;
import com.google.gwt.inject.client.Ginjector;

@GinModules(ClientModule.class)
public interface GIN extends Ginjector {
	AsyncCallbackGinjector getAsyncCallbackGinjector();
}
