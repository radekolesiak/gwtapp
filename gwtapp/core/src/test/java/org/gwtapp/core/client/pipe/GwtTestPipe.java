package org.gwtapp.core.client.pipe;

import org.gwtapp.core.client.GWTTestCore;
import org.gwtapp.core.client.Pipe;
import org.gwtapp.core.client.PipeHandler;
import org.gwtapp.core.client.PipeManager;
import org.gwtapp.core.rpc.data.Value;
import org.junit.Test;

public class GwtTestPipe extends GWTTestCore {

	public static class TestPipe extends Pipe<Boolean> {
		public TestPipe(PipeHandler<Boolean> handler) {
			super(handler);
		}
	}

	@Test
	public void testPipeHandlers() {
		final Value<Boolean> v1 = new Value<Boolean>();
		final Value<Boolean> v2 = new Value<Boolean>();
		PipeManager m1 = new PipeManager();
		PipeManager m2 = new PipeManager();
		PipeHandler<Boolean> h1 = new PipeHandler<Boolean>() {
			@Override
			public void onChangeValue(Boolean value) {
				v1.set(value);
			}
		};
		PipeHandler<Boolean> h2 = new PipeHandler<Boolean>() {
			@Override
			public void onChangeValue(Boolean value) {
				v2.set(value);
			}
		};
		TestPipe p1 = new TestPipe(h1);
		TestPipe p2 = new TestPipe(h2);
		m1.addPipe(TestPipe.class, p1);
		m2.addPipe(TestPipe.class, p2);
		{
			assertNull(v1.get());
			assertNull(v2.get());
			m1.setValue(TestPipe.class, true);
			assertNull(v1.get());
			assertNull(v2.get());
			m2.setValue(TestPipe.class, false);
			assertNull(v1.get());
			assertNull(v2.get());
		}
		m1.connect();
		m2.connect();
		{
			m1.setValue(TestPipe.class, true);
			assertNull(v1.get());
			assertTrue(v2.get());
			m2.setValue(TestPipe.class, false);
			assertFalse(v1.get());
			assertTrue(v2.get());
		}
		m2.disconnect();
		{
			m1.setValue(TestPipe.class, false);
			assertFalse(v1.get());
			assertTrue(v2.get());			
		}
		m2.connect();
		{
			m1.setValue(TestPipe.class, false);
			assertFalse(v1.get());
			assertFalse(v1.get());
		}
	}
}
