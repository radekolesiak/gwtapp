package org.gwtapp.ccalc.client.data.book;

import java.io.Serializable;
import java.util.List;

import org.gwtapp.ccalc.client.data.book.metafields.page.NameMetaField;
import org.gwtapp.ccalc.client.data.book.metafields.page.OperationsMetaField;
import org.gwtapp.core.rpc.data.ModelData;

import com.google.gwt.user.client.rpc.IsSerializable;

public interface Page extends ModelData, IsSerializable, Serializable {

	public final static NameMetaField NAME = new NameMetaField();
	public final static OperationsMetaField OPERATIONS = new OperationsMetaField();

	void setName(String name);

	String getName();

	void setOperations(List<Operation> operations);

	List<Operation> getOperations();
}
