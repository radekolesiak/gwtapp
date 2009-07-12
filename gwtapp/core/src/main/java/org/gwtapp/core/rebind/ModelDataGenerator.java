package org.gwtapp.core.rebind;

import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Set;

import com.google.gwt.core.ext.Generator;
import com.google.gwt.core.ext.GeneratorContext;
import com.google.gwt.core.ext.TreeLogger;
import com.google.gwt.core.ext.UnableToCompleteException;
import com.google.gwt.core.ext.typeinfo.JClassType;
import com.google.gwt.core.ext.typeinfo.TypeOracle;
import com.google.gwt.user.rebind.ClassSourceFileComposerFactory;
import com.google.gwt.user.rebind.SourceWriter;
import com.helionprime.jruntime.RuntimeClass;
import com.helionprime.jruntime.RuntimeClassException;

public class ModelDataGenerator extends Generator {

	// http://groups.google.com/group/Google-Web-Toolkit/msg/ae249ea67c2c3435

	/** Simple name of class to be generated */
	private String className = null;

	/** Package name of class to be generated */
	private String packageName = null;

	/** Fully qualified class name passed into GWT.create() */
	private String typeName = null;

	private final static String SUPER_CLASS = "org.gwtapp.core.client.data.HashIterableModelData";
	private final static String CLASS_NAME_SUFFIX = "Wrapper";

	@Override
	public String generate(TreeLogger logger, GeneratorContext context,
			String typeName) throws UnableToCompleteException {
		this.typeName = typeName;
		System.out.println("GENERATOR: " + typeName + " -> " + packageName
				+ "." + className);

		TypeOracle typeOracle = context.getTypeOracle();
		try {
			// get classType and save instance variables
			JClassType classType = typeOracle.getType(typeName);
			packageName = classType.getPackage().getName();
			className = classType.getSimpleSourceName() + CLASS_NAME_SUFFIX;
			// Generate class source code
			generateClass(logger, context);
		} catch (Exception e) {
			logger.log(TreeLogger.ERROR, "ModelDataGenerator ERROR!!!", e);
		}

		return packageName + "." + className;
	}

	private void generateClass(TreeLogger logger, GeneratorContext context)
			throws ClassNotFoundException {
		// get print writer that receives the source code
		PrintWriter printWriter = null;
		printWriter = context.tryCreate(logger, packageName, className);
		// print writer if null, source code has ALREADY been generated,return
		if (printWriter == null) {
			return;
		}

		// init composer, set class properties, create source writer
		ClassSourceFileComposerFactory composer = null;
		composer = new ClassSourceFileComposerFactory(packageName, className);
		if (Class.forName(typeName).isInterface()) {
			composer.addImplementedInterface(typeName);
			composer.setSuperclass(SUPER_CLASS);
		} else {
			composer.setSuperclass(typeName);
		}
		SourceWriter sourceWriter = null;
		sourceWriter = composer.createSourceWriter(context, printWriter);
		sourceWriter.println(generateContent(typeName, className));
		// close generated class
		sourceWriter.outdent();
		sourceWriter.println("}");

		// commit generated class
		context.commit(logger, printWriter);
	}

	private static String generateContent(String typeName, String className)
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
		
		System.out.println(s);
		return s.toString();
	}

	@SuppressWarnings("unchecked")
	public static <T> T bind(Class<T> c) {
		System.out.println(System.getProperty("java.class.path"));
		try {
			RuntimeClass myFirstRuntimeClass = new RuntimeClass(
					generateToBind(c));
			try {
				return (T) myFirstRuntimeClass.newInstance();
			} catch (InstantiationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RuntimeClassException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
