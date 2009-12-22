package org.gwtapp.startapp.server.html;

import org.gwtapp.core.client.html.ui.core.HPanel;
import org.gwtapp.core.server.html.HSerializer;
import org.gwtapp.core.server.html.HSerializerException;
import org.junit.Assert;
import org.junit.Test;

public class SerializerTest {

	@Test
	public void serializeTest() throws HSerializerException {
		HPanel widget = new HPanel();
		widget.setId("h1");
		String s = HSerializer.success(widget);
		Assert.assertNotNull(s);
		System.out.println(s);
		System.out.println(HSerializer.encode(s));
	}
}
