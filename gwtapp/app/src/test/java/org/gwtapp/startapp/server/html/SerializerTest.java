package org.gwtapp.startapp.server.html;

import org.gwtapp.core.rpc.html.ui.core.HPanel;
import org.gwtapp.core.server.html.core.HServer;
import org.gwtapp.core.server.html.core.HServerException;
import org.junit.Assert;
import org.junit.Test;

public class SerializerTest {

	@Test
	public void serializeTest() throws HServerException {
		HPanel widget = new HPanel();
		widget.setId("h1");
		String s = HServer.success(widget);
		Assert.assertNotNull(s);
		System.out.println(s);
		System.out.println(HServer.encode(s));
	}
}
