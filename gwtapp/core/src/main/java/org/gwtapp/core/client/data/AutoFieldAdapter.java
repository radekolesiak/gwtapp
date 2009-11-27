package org.gwtapp.core.client.data;

public abstract class AutoFieldAdapter<M, T> implements AutoField<M, T> {

	private final String name;

	public AutoFieldAdapter(String name) {
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
	public AutoField<M, T> add(HasAutoField model){
		model.addAutoField(this);
		return this;
	}
}
