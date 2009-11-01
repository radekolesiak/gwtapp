package org.gwtapp.core.client.data;

public abstract class AbstractModelData implements ModelData {

	@Override
	public Object remove(String property) {
		return set(property, null);
	}

	private boolean equalsAB(Object a, Object b) {
		if (a == b) {
			return true;
		} else if (a == null) {
			return false;
		} else {
			return a.equals(b);
		}
	}

	private int getHashCodeA(final Object a) {
		if (a != null) {
			return a.hashCode();
		} else {
			return 0;
		}
	}

	@Override
	public int hashCode() {
		final int prime = 53;
		int hashCode = super.hashCode();
		for (String property : getPropertyNames()) {
			hashCode = (hashCode + getHashCodeA(get(property))) * prime;
		}
		return hashCode;
	}

	@Override
	public boolean equals(Object o) {
		if (o instanceof ModelData) {
			ModelData m = (ModelData) o;
			for (String property : getPropertyNames()) {
				if (!equalsAB(get(property), m.get(property))) {
					return false;
				}
			}
			return true;
		}
		return false;
	}
}
