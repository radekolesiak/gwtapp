package org.gwtapp.ccalc.rpc.data.book;

import java.io.Serializable;
import java.util.List;

import org.gwtapp.core.rpc.data.HashModelData;

public class PageImpl extends HashModelData implements Page, Serializable {

	private static final long serialVersionUID = 1686394022907553621L;
	
	private String name = NAME.add(this).def();
	private List<Operation> operations = OPERATIONS.add(this).def();

	@Override
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void setOperations(List<Operation> operations) {
		this.operations = operations;
	}

	@Override
	public List<Operation> getOperations() {
		return operations;
	}
}
