package org.gwtapp.core.client;

import org.gwtapp.core.client.api.AService;
import org.gwtapp.core.client.api.AServiceAsync;
import org.gwtapp.core.client.data.A;
import org.gwtapp.core.client.data.B;
import org.junit.Test;

import com.google.gwt.core.client.GWT;

public class GwtTestRPC extends GWTTestCore {
	@Test
	public void testRPCFromServer() {
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

	@Test
	public void testRPCFromClient() {
		A a1 = new A();
		A a3 = new A();
		B b = new B();
		a1.setValue("abc");
		a3.setValue("abc");
		b.setA1(a1);
		b.setA2(a1);
		b.setA3(a3);
		AServiceAsync service = GWT.create(AService.class);
		service.setB(b, new SimpleAsyncCallback<Boolean>() {
			public void onSuccess(Boolean b) {
				assertTrue(b);
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
