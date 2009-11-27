package org.gwtapp.core.client.data;

public interface AutoField<M> {

	public String name();

	public void set(M model, Object value);

	public Object get(M model);
}
