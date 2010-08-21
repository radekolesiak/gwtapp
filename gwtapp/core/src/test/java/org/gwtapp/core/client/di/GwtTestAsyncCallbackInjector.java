package org.gwtapp.core.client.di;

import org.gwtapp.core.client.GWTTestCore;
import org.gwtapp.core.client.SimpleAsyncCallback;
import org.gwtapp.core.rpc.exception.RpcException;
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

	@Test
	public void testThrownException() {
		GinjectorService gin = GWT.create(GinjectorService.class);
		SomeClass some = gin.getSomeClass();
		AsyncCallbackAdapter<String> callback = (AsyncCallbackAdapter<String>) some.getInjector().create(
				new SimpleAsyncCallback<String>() {
					@Override
					public void onFailure(Throwable e) {
						assertEquals(IllegalStateException.class, e.getClass());
						throw new RpcException();
					}
				});
		assertNotNull(callback);
		callback.setOwner(this);
		callback.setExpectedTest(true);
		callback.setExcpectedException(RpcException.class);
		delayTestFinish(250);
		callback.onFailure(new IllegalStateException());
	}
	
	public void doFinishTest(){
		finishTest();
	}
}
