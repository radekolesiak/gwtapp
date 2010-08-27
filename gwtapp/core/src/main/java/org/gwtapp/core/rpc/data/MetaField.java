package org.gwtapp.core.rpc.data;

/**
 * 
 * @author Radek
 * 
 * @param <M>
 *            Java Bean model
 * @param <T>
 *            Field type
 * 
 *            The AutoField is an annotation and reflection replacement of RPC
 *            and Java Bean field's properties for RPC and ModelData data.
 */
public interface MetaField<M, T> {

	/** Field name */
	String name();

	/** Sets a value to the model setter. */
	void set(M model, T value);

	/** Gets a value from the model getter. */
	T get(M model);

	/** Default field value. */
	T def();

	/** Adds it self to the 'HasAutoField' object. */
	MetaField<M, T> add(HasMetaField model);
}
