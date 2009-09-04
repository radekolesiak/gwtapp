package org.gwtapp.startapp.client;

import org.gwtapp.core.client.html.core.Deserializer;
import org.gwtapp.core.client.html.ui.HPanel;
import org.junit.Test;

import com.google.gwt.junit.client.GWTTestCase;
import com.google.gwt.user.client.rpc.SerializationException;

public class GwtTestDeserializer extends GWTTestCase {

	@Override
	public String getModuleName() {
		return "org.gwtapp.startapp.StartApp";
	}
	
	@Test
	public void testDeserializer1() throws SerializationException{
		String serialized = "//OK[4,0,2,3,0,2,1,[\"org.gwtapp.core.client.html.ui.HPanel/746786425\",\"java.util.ArrayList/3821976829\",\"h1\",\"div\"],0,5]";
		HPanel panel = Deserializer.getSerializedObject(serialized);
		assertNotNull(panel);
		assertEquals("h1", panel.getId());
		assertEquals("div", panel.getTag());
	}

}
