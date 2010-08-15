package org.gwtapp.ccalc.client.data.book.metafields.page;

import java.util.ArrayList;
import java.util.List;

import org.gwtapp.ccalc.client.data.book.Operation;
import org.gwtapp.ccalc.client.data.book.Page;
import org.gwtapp.core.rpc.data.MetaFieldAdapter;


public class OperationsMetaField extends MetaFieldAdapter<Page, List<Operation>> {

	public OperationsMetaField() {
		this("operations");
	}

	public OperationsMetaField(String name) {
		super(name);
	}

	@Override
	public List<Operation> get(Page model) {
		return model.getOperations();
	}

	@Override
	public void set(Page model, List<Operation> value) {
		model.setOperations((List<Operation>) value);
	}
	
	@Override
	public List<Operation> def() {
		return new ArrayList<Operation>();
	}

}
