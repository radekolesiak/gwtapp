package org.gwtapp.core.client;

import org.gwtapp.core.client.api.AService;
import org.gwtapp.core.client.api.AServiceAsync;
import org.gwtapp.core.client.data.B;
import org.junit.Test;

import com.google.gwt.core.client.GWT;

public class GwtTestRPC extends GWTTestCore {
	@Test
	public void testRPC() {
		AServiceAsync service = GWT.create(AService.class);
		service.getB(new SimpleAsyncCallback<B>() {
			public void onSuccess(B b) {
				assertTrue(b.getA1() == b.getA2());
				assertFalse(b.getA1() == b.getA3());
				finishTest();
			}

			@Override
			public void onFailure(Throwable e) {
				e.printStackTrace();
			}
		});
		delayTestFinish(3500);
	}
}
