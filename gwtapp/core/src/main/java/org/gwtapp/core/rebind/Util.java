package org.gwtapp.core.rebind;

import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Set;

import com.helionprime.jruntime.RuntimeClass;

public class Util {

	public final static String SUPER_CLASS = "org.gwtapp.core.client.data.HashModelData";
	public final static String CLASS_NAME_SUFFIX = "Wrapper";

	public static String generateContent(String typeName, String className)
			throws SecurityException, ClassNotFoundException {
		StringBuffer buffer = new StringBuffer();
		generateConstructor(buffer, className);
		if (Class.forName(typeName).isInterface()) {
			Set<String> properties = new HashSet<String>();
			for (Method method : Class.forName(typeName).getMethods()) {
				generateMethod(buffer, properties, method);
			}
			generateMethodGetPropertyNames(buffer, properties);
		}
		return buffer.toString();
	}

	private static void generateConstructor(StringBuffer sourceWriter,
			String className) {
		sourceWriter.append("public " + className + "() { ");
		sourceWriter.append("\n");
		sourceWriter.append("super();");
		sourceWriter.append("\n");
		sourceWriter.append("}");
		sourceWriter.append("\n");
	}

	private static String getPropertyName(String methodName) {
		String property = methodName.substring(3);
		return property.substring(0, 1).toLowerCase() + property.substring(1);
	}

	private static void generateMethod(StringBuffer sourceWriter,
			Set<String> properties, Method method) {
		String name = method.getName();
		if (name.equals("getPropertyNames") || name.equals("copyTo")
				|| name.equals("remove")) {
			return;
		}
		if (name.startsWith("get") || name.startsWith("set")) {
			if (name.equals("get") || name.equals("set")) {
				return;
			}
			properties.add(getPropertyName(name));
			if (name.startsWith("get")) {
				generateMethodGet(sourceWriter, method);
			} else {
				generateMethodSet(sourceWriter, method);
			}
		} else {
			throw new IllegalStateException(
					"method must starts with 'set' or 'get': " + name);
		}
	}

	private static void generateMethodGetPropertyNames(
			StringBuffer sourceWriter, Set<String> properties) {
		String s = "";
		for (String property : properties) {
			if (!s.isEmpty()) {
				s += ",";
			}
			s += "\"" + property + "\"";
		}
		String[] a = new String[] { "a" };
		java.util.Arrays.asList(a);
		sourceWriter
				.append("public java.util.Collection<java.lang.String> getPropertyNames() {");
		sourceWriter
				.append("return java.util.Arrays.asList(new java.lang.String[]{"
						+ s + "});");
		sourceWriter.append("}");
	}

	private static void generateMethodGet(StringBuffer sourceWriter,
			Method method) {
		String name = method.getName();
		String type = method.getReturnType().getName();
		sourceWriter.append("public " + type + " " + name + "() { ");
		sourceWriter.append("\n");
		sourceWriter.append("return (" + type + ") get(\""
				+ getPropertyName(name) + "\");");
		sourceWriter.append("\n");
		sourceWriter.append("}");
		sourceWriter.append("\n");
	}

	private static void generateMethodSet(StringBuffer sourceWriter,
			Method method) {
		String name = method.getName();
		String type = method.getParameterTypes()[0].getName();
		sourceWriter.append("public void " + name + "(" + type + " value) { ");
		sourceWriter.append("\n");
		sourceWriter.append("set(\"" + getPropertyName(name) + "\", value);");
		sourceWriter.append("\n");
		sourceWriter.append("}");
		sourceWriter.append("\n");
	}

	@SuppressWarnings("unchecked")
	private static String generateToBind(Class c) throws SecurityException,
			ClassNotFoundException {
		StringBuffer s = new StringBuffer();

		String className = c.getSimpleName() + CLASS_NAME_SUFFIX;
		String typeName = c.getName();
		s.append("package " + c.getPackage().getName() + ";");
		s.append("\n");
		s.append("public class " + className + " ");
		s.append("\n");
		if (c.isInterface()) {
			s.append(" extends " + SUPER_CLASS + " ");
			s.append("\n");
			s.append(" implements " + c.getName() + " ");
			s.append("\n");
		} else {
			s.append(" extends " + c.getName() + " ");
			s.append("\n");
		}
		s.append("{");
		s.append("\n");
		s.append(generateContent(typeName, className));
		s.append("\n");
		s.append("}");
		s.append("\n");
		return s.toString();
	}

	@SuppressWarnings("unchecked")
	public static <T> T bind(Class<T> c) {
		try {
			RuntimeClass myFirstRuntimeClass = new RuntimeClass(
					generateToBind(c));
			return (T) myFirstRuntimeClass.newInstance();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
