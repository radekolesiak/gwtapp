package org.gwtapp.core.client.utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.TreeMap;
import java.util.TreeSet;

import org.gwtapp.core.client.data.IsClonable;

public class Data {
	// manual copy
	@SuppressWarnings("unchecked")
	public static Object copy(Object o) {
		if (o == null) {
			return null;
		} else if (o instanceof IsClonable) {
			return ((IsClonable) o).cloneTo();
		} else if (o instanceof String) {
			return new String((String) o);
		} else if (o instanceof Integer) {
			return new Integer((Integer) o);
		} else if (o instanceof Byte) {
			return new Byte((Byte) o);
		} else if (o instanceof Long) {
			return new Long((Long) o);
		} else if (o instanceof Float) {
			return new Float((Float) o);
		} else if (o instanceof Double) {
			return new Double((Double) o);
		} else if (o instanceof Collection) {
			Collection c;
			if (o instanceof LinkedList) {
				c = new LinkedList();
			} else if (o instanceof ArrayList) {
				c = new ArrayList();
			} else if (o instanceof HashSet) {
				c = new HashSet();
			} else if (o instanceof TreeSet) {
				c = new TreeSet();
			} else {
				throw new IllegalArgumentException();
			}
			for (Object item : (Collection) o) {
				c.add(copy(item));
			}
			return c;
		} else if (o instanceof Map) {
			Map c;
			if (o instanceof HashMap) {
				c = new HashMap();
			} else if (o instanceof TreeMap) {
				c = new TreeMap();
			} else if (o instanceof LinkedHashMap) {
				c = new LinkedHashMap();
			} else {
				throw new IllegalArgumentException();
			}
			for (Object key : ((Map) o).keySet()) {
				c.put(copy(key), copy(((Map) o).get(key)));
			}
			return c;
		} else {
			throw new IllegalArgumentException();
		}
	}
}
