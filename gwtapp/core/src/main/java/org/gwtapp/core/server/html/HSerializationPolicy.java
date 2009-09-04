package org.gwtapp.core.server.html;

import com.google.gwt.user.client.rpc.SerializationException;
import com.google.gwt.user.server.rpc.SerializationPolicy;

public class HSerializationPolicy extends SerializationPolicy {

	@Override
	public boolean shouldDeserializeFields(Class<?> c) {
		return isFieldSerializable(c);
	}

	@Override
	public boolean shouldSerializeFields(Class<?> c) {
		return isFieldSerializable(c);
	}

	@Override
	public void validateDeserialize(Class<?> c) throws SerializationException {
	}

	@Override
	public void validateSerialize(Class<?> c) throws SerializationException {
	}

	private boolean isFieldSerializable(Class<?> c) {
		if (c==null||c.isPrimitive()) {
			return true;
		} else {
			return true;
		}
	}
}
