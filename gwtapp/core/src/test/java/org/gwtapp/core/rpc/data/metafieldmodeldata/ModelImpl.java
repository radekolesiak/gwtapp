package org.gwtapp.core.rpc.data.metafieldmodeldata;

import org.gwtapp.core.rpc.data.HashModelData;

public class ModelImpl extends HashModelData implements Model {

	private String test = Model.PROPERTY.add(this).def();

	@Override
	public String getProperty() {
		return test;
	}

	@Override
	public void setProperty(String test) {
		this.test = test;
	}

}
