package org.gwtapp.startapp.client.data;


import org.gwtapp.core.client.data.ModelData;

import com.google.gwt.user.client.rpc.IsSerializable;

public interface TestModel extends ModelData, IsSerializable {

	final static String TEXT = "text";

	void setText(String text);

	String getText();
}
