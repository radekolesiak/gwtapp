package org.gwtapp.core.client.di;

import org.gwtapp.core.client.GWTTestCore;
import org.gwtapp.core.client.SimpleAsyncCallback;
import org.junit.Test;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;

public class GwtTestAsyncCallbackInjector extends GWTTestCore {

	@Test
	public void testInitState() {
		GinjectorService gin = GWT.create(GinjectorService.class);
		assertNotNull(gin);
		SomeClass some = gin.getSomeClass();
		assertNotNull(some);
		assertNotNull(some.getInjector());
	}

	@Test
	public void testException() {
		GinjectorService gin = GWT.create(GinjectorService.class);
		SomeClass some = gin.getSomeClass();
		AsyncCallback<String> callback = some.getInjector().create(
				new SimpleAsyncCallback<String>() {
					@Override
					public void onFailure(Throwable e) {
						assertEquals(IllegalStateException.class, e.getClass());
						finishTest();
					}
				});
		assertNotNull(callback);
		delayTestFinish(250);
		callback.onFailure(new IllegalStateException());
	}
}
