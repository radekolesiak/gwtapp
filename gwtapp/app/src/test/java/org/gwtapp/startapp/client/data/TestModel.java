package org.gwtapp.startapp.client.data;

import java.util.List;

import org.gwtapp.core.client.data.ModelData;

import com.google.gwt.user.client.rpc.IsSerializable;

public interface TestModel extends ModelData, IsSerializable {

	final static String TEXT = "text";

	final static String NUMBER = "number";

	final static String DOUBLES = "doubles";
	
	void setText(String text);

	String getText();
	
	Integer getNumber();
	
	List<Double> getDoubles();
}
