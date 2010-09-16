package org.gwtapp.core.client.pipe;

import org.gwtapp.core.client.GWTTestCore;
import org.gwtapp.core.client.Pipe;
import org.gwtapp.core.client.PipeHandler;
import org.gwtapp.core.client.PipeManager;
import org.junit.Test;

public class GwtTestPipe extends GWTTestCore {

	public static class TestPipe extends Pipe<Boolean> {
		public TestPipe(PipeHandler<Boolean> handler) {
			super(handler);
		}
	}

	@Test
	public void test() {
		PipeManager m1 = new PipeManager();
		PipeManager m2 = new PipeManager();
		PipeHandler<Boolean> h1 = new PipeHandler<Boolean>() {
			@Override
			public void onChangeValue(Boolean value) {

			}
		};
		PipeHandler<Boolean> h2 = new PipeHandler<Boolean>() {
			@Override
			public void onChangeValue(Boolean value) {

			}
		};
		TestPipe p1 = new TestPipe(h1);
		TestPipe p2 = new TestPipe(h2);
		m1.addPipe(TestPipe.class, p1);
		m2.addPipe(TestPipe.class, p2);
	}
}
