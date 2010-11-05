package org.gwtapp.io.server;

import org.gwtapp.core.rpc.exception.RpcException;
import org.gwtapp.io.client.Bean;
import org.junit.Assert;
import org.junit.Test;

public class IOServerTest {

	@Test
	public void testEncode() {
		Assert.assertEquals("ab", IOServer.encode("ab"));
		Assert.assertEquals("a&amp;b", IOServer.encode("a&b"));
		Assert.assertEquals("a b", IOServer.encode("a b"));
	}

	@Test
	public void testSuccessSerializeSerializable() throws IOServerException {
		Assert.assertEquals(
				"//OK[2,1,[\"java.lang.String/2004016611\",\"xyz\"],0,6]",
				IOServer.success("xyz"));
	}

	@Test
	public void testSuccessSerializeIsSerializable() throws IOServerException {
		Assert.assertEquals(
				"//OK[0,0,0,1,[\"org.gwtapp.io.client.Bean/2013084133\"],0,6]",
				IOServer.success(new Bean()));
		Assert
				.assertEquals(
						"//OK[12,0,2,1,[\"org.gwtapp.io.client.Bean/2013084133\",\"aba\"],0,6]",
						IOServer.success(new Bean("aba", null, 12)));
	}

	@Test
	public void testFailureSerialize() throws IOServerException {
		Assert
				.assertEquals(
						"//EX[0,1,[\"org.gwtapp.core.rpc.exception.RpcException/1875557212\"],0,6]",
						IOServer.failure(new RpcException()));
	}
}
