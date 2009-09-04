package org.gwtapp.startapp.server.html;

import org.gwtapp.core.client.html.ui.core.HPanel;
import org.gwtapp.core.server.html.HSerializer;
import org.junit.Assert;
import org.junit.Test;

import com.google.gwt.user.client.rpc.SerializationException;

public class SerializerTest {

	@Test
	public void serializeTest() throws SecurityException, SerializationException, NoSuchMethodException{
		HSerializer serializer = new HSerializer();
		HPanel widget = new HPanel();
		widget.setId("h1");
		String s = serializer.serialize(widget);
		Assert.assertNotNull(s);
		System.out.println(s);
	}
}
