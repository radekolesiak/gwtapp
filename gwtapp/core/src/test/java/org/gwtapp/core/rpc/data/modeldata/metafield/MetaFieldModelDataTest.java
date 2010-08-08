package org.gwtapp.core.rpc.data.modeldata.metafield;

import junit.framework.Assert;

import org.gwtapp.core.rpc.data.HasMetaField;
import org.gwtapp.core.rpc.data.HashModelData;
import org.gwtapp.core.rpc.data.MetaField;
import org.gwtapp.core.rpc.data.ModelData;
import org.junit.Test;

public class MetaFieldModelDataTest {

	@Test
	public void testPropertyMetaField() {
		PropertyMetaField pmf = new PropertyMetaField();
		Assert.assertEquals("property", pmf.name());
		Assert.assertEquals("value", pmf.def());
	}

	@Test
	@SuppressWarnings("unchecked")
	public void testPropertyMetaFieldImplementsMetaField() {
		PropertyMetaField pmf = new PropertyMetaField();
		Assert.assertTrue(pmf instanceof MetaField);
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

	@Test
	public void testHashModelDataHasMetaField() {
		HashModelData model = new ModelImpl();
		Assert.assertTrue(model instanceof HasMetaField);
	}

	@Test
	public void testHashModelDataImplementsModelData() {
		HashModelData model = new ModelImpl();
		Assert.assertTrue(model instanceof ModelData);
	}
}
