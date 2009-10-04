package org.gwtapp.startapp.client;

import org.gwtapp.core.client.html.core.Deserializer;
import org.gwtapp.core.client.html.ui.core.HPanel;
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
		String serialized = "Ly9PS1s0LDAsMiwwLDMsMCwyLDEsWyJvcmcuZ3d0YXBwLmNvcmUuY2xpZW50Lmh0bWwudWkuY29yZS5IUGFuZWwvMjI1NTYzODk5OCIsImphdmEudXRpbC5BcnJheUxpc3QvMzgyMTk3NjgyOSIsImgxIiwiZGl2Il0sMCw1XQ==";
		HPanel panel = Deserializer.getSerializedObject(Deserializer.decode(serialized));
		assertNotNull(panel);
		assertEquals("h1", panel.getId());
		assertEquals("div", panel.getTag());
	}

}
