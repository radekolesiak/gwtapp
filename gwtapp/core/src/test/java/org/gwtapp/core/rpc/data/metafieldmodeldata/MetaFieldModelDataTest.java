package org.gwtapp.core.rpc.data.metafieldmodeldata;

import junit.framework.Assert;

import org.gwtapp.core.rpc.data.HashModelData;
import org.junit.Test;

public class MetaFieldModelDataTest {

	@Test
	public void testPropertyMetaField() {
		PropertyMetaField pmf = new PropertyMetaField();
		Assert.assertEquals("property", pmf.name());
		Assert.assertEquals("value", pmf.def());
	}

	@Test
	public void testBeanSetterAndGetter() {
		Model model = new ModelImpl();
		Assert.assertEquals("value", model.getProperty());
	}

	@Test
	public void testMapSetterAndGetter() {
		HashModelData model = new ModelImpl();
		Assert.assertEquals("value", model.get(Model.PROPERTY.name()));
	}

}
