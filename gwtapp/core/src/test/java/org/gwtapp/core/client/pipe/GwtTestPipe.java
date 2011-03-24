package org.gwtapp.core.client.pipe;

import org.gwtapp.core.client.GWTTestCore;
import org.gwtapp.core.rpc.data.Value;
import org.junit.Test;

public class GwtTestPipe extends GWTTestCore {

	public static class TestPipe extends Pipe<Boolean> {

		public TestPipe() {
		}

		public TestPipe(PipeHandler<Boolean> handler) {
			super(handler);
		}
	}

	@Test
	public void testPipeHandlers() {
		PipeManager.resetAll();
		final Value<Boolean> v1 = new Value<Boolean>();
		final Value<Boolean> v2 = new Value<Boolean>();
		PipeManager m1 = new PipeManager();
		PipeManager m2 = new PipeManager();
		PipeHandler<Boolean> h1 = new PipeHandler<Boolean>() {
			@Override
			public void onValueChanged(Boolean value) {
				v1.set(value);
			}
		};
		PipeHandler<Boolean> h2 = new PipeHandler<Boolean>() {
			@Override
			public void onValueChanged(Boolean value) {
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
			assertFalse(v2.get());
		}
	}

	@Test
	public void testPipeGetter() {
		PipeManager.resetAll();
		PipeManager m1 = new PipeManager();
		PipeManager m2 = new PipeManager();
		TestPipe p1 = new TestPipe();
		TestPipe p2 = new TestPipe();
		m1.addPipe(TestPipe.class, p1);
		m2.addPipe(TestPipe.class, p2);
		{
			assertNull(PipeManager.getBroadcastValue(TestPipe.class));
			m1.setValue(TestPipe.class, true);
			assertNull(PipeManager.getBroadcastValue(TestPipe.class));
			m2.setValue(TestPipe.class, false);
			assertNull(PipeManager.getBroadcastValue(TestPipe.class));
		}
		m1.connect();
		m2.connect();
		{
			assertNull(PipeManager.getBroadcastValue(TestPipe.class));
			m1.setValue(TestPipe.class, true);
			assertTrue(PipeManager.getBroadcastValue(TestPipe.class));
			m2.setValue(TestPipe.class, false);
			assertFalse(PipeManager.getBroadcastValue(TestPipe.class));
		}
		m2.disconnect();
		{
			m1.setValue(TestPipe.class, null);
			assertNull(PipeManager.getBroadcastValue(TestPipe.class));
		}
		m2.connect();
		{
			m1.setValue(TestPipe.class, true);
			assertTrue(PipeManager.getBroadcastValue(TestPipe.class));
		}
	}

	@Test
	public void testPipeSetter() {
		PipeManager.resetAll();
		assertNull(PipeManager.getBroadcastValue(TestPipe.class));
		PipeManager.setBroadcastValue(TestPipe.class, true);
		assertTrue(PipeManager.getBroadcastValue(TestPipe.class));
		PipeManager.setBroadcastValue(TestPipe.class, false);
		assertFalse(PipeManager.getBroadcastValue(TestPipe.class));
	}

	@Test
	public void testPipeSetterHandlers() {
		PipeManager.resetAll();
		final Value<Boolean> v1 = new Value<Boolean>();
		final Value<Boolean> v2 = new Value<Boolean>();
		PipeManager m1 = new PipeManager();
		PipeManager m2 = new PipeManager();
		PipeHandler<Boolean> h1 = new PipeHandler<Boolean>() {
			@Override
			public void onValueChanged(Boolean value) {
				v1.set(value);
			}
		};
		PipeHandler<Boolean> h2 = new PipeHandler<Boolean>() {
			@Override
			public void onValueChanged(Boolean value) {
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
			assertNull(PipeManager.getBroadcastValue(TestPipe.class));
			PipeManager.setBroadcastValue(TestPipe.class, true);
			assertNull(v1.get());
			assertNull(v2.get());
			assertTrue(PipeManager.getBroadcastValue(TestPipe.class));
			m2.setValue(TestPipe.class, false);
			assertNull(v1.get());
			assertNull(v2.get());
			assertTrue(PipeManager.getBroadcastValue(TestPipe.class));
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
			assertFalse(PipeManager.getBroadcastValue(TestPipe.class));
		}
		m2.connect();
		{
			m1.setValue(TestPipe.class, false);
			assertFalse(v1.get());
			assertFalse(v2.get());
			assertFalse(PipeManager.getBroadcastValue(TestPipe.class));
		}
	}


	@Test
	public void testSetEnabledDisabled() {
		PipeManager.resetAll();
		final Value<Boolean> v1 = new Value<Boolean>();
		final Value<Boolean> v2 = new Value<Boolean>();
		PipeManager m1 = new PipeManager();
		PipeManager m2 = new PipeManager();
		PipeHandler<Boolean> h1 = new PipeHandler<Boolean>() {
			@Override
			public void onValueChanged(Boolean value) {
				v1.set(value);
			}
		};
		PipeHandler<Boolean> h2 = new PipeHandler<Boolean>() {
			@Override
			public void onValueChanged(Boolean value) {
				v2.set(value);
			}
		};
		TestPipe p1 = new TestPipe(h1);
		TestPipe p2 = new TestPipe(h2);
		m1.setEnabled(false);
		m1.addPipe(TestPipe.class, p1);
		m2.addPipe(TestPipe.class, p2);
		{
			assertNull(v1.get());
			assertNull(v2.get());
			m1.setValue(TestPipe.class, true);
			assertNull(v1.get());
			assertNull(v2.get());
			assertNull(PipeManager.getBroadcastValue(TestPipe.class));
			PipeManager.setBroadcastValue(TestPipe.class, true);
			assertNull(v1.get());
			assertNull(v2.get());
			assertTrue(PipeManager.getBroadcastValue(TestPipe.class));
			m2.setValue(TestPipe.class, false);
			assertNull(v1.get());
			assertNull(v2.get());
			assertTrue(PipeManager.getBroadcastValue(TestPipe.class));
		}
		m1.connect();
		m2.connect();
		{
			m1.setValue(TestPipe.class, true);
			assertNull(v1.get());
			assertNull(v2.get());
			m2.setValue(TestPipe.class, false);
			assertNull(v1.get());
			assertNull(v2.get());
		}
		m1.setEnabled(true);
		m2.disconnect();
		{
			m1.setValue(TestPipe.class, false);
			assertNull(v1.get());
			assertNull(v2.get());
			assertFalse(PipeManager.getBroadcastValue(TestPipe.class));
		}
		m2.connect();
		{
			m1.setValue(TestPipe.class, true);
			assertNull(v1.get());
			assertTrue(v2.get());
			assertTrue(PipeManager.getBroadcastValue(TestPipe.class));
		}
	}
}
