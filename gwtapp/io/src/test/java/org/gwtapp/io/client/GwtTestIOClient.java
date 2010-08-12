package org.gwtapp.io.client;

import org.junit.Test;

public class GwtTestIOClient extends IOTest {

	@Test
	public void testDecode() {
		assertEquals("a&b", IOClient.decode("a&b"));
		assertEquals("a&b", IOClient.decode("a&amp;b"));
	}
}
