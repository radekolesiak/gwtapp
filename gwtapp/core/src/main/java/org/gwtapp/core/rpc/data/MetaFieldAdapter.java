package org.gwtapp.core.rpc.data;

public abstract class MetaFieldAdapter<M, T> implements MetaField<M, T> {

	private final String name;

	public MetaFieldAdapter(String name) {
		this.name = name;
	}

	@Override
	public String name() {
		return name;
	}

	@Override
	public T def() {
		return null;
	}
	
	@Override
	public MetaField<M, T> add(HasMetaField model){
		model.addMetaField(this);
		return this;
	}
}
