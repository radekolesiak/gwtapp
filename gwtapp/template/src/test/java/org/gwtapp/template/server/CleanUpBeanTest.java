package org.gwtapp.template.server;

import org.junit.Assert;
import org.junit.Test;

public class CleanUpBeanTest {

	@Test
	public void testCleanUpBean() {
		CleanUpBean cleanUp = new CleanUpBean();
		Assert.assertNull(cleanUp.getText());
		cleanUp.setText("abc");
		Assert.assertEquals("abc", cleanUp.getText());
		cleanUp.setText("xyz");
		Assert.assertEquals("xyz", cleanUp.getText());
		cleanUp.setText("x\ny\rz");
		Assert.assertEquals("xyz", cleanUp.getText());
		cleanUp.setText("xy\rz");
		Assert.assertEquals("xyz", cleanUp.getText());
		cleanUp.setText("x\nyz");
		Assert.assertEquals("xyz", cleanUp.getText());
	}
}
