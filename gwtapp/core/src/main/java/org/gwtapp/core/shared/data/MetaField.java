package org.gwtapp.core.shared.data;

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

	/** Field's name */
	String name();

	/** Sets a value to the model's setter. */
	void set(M model, Object value);

	/** Gets a value from the model's getter. */
	Object get(M model);

	/** Default field's value. */
	T def();

	/** Adds it self to the 'HasAutoField' object. */
	MetaField<M, T> add(HasMetaField model);
}
