package org.gwtapp.core.client.di;

import org.gwtapp.core.client.GWTTestCore;
import org.junit.Test;

import com.google.gwt.core.client.GWT;

public class GwtTestAsyncCallbackInjector extends GWTTestCore {

	@Test
	public void testInitState() {
		GinjectorService gin = GWT.create(GinjectorService.class);
		assertNotNull(gin);
		SomeClass some = gin.getSomeClass();
		assertNotNull(some);
		assertNotNull(some.getInjector());
	}
}
