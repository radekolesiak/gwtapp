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

public class ModelDataGenerator extends Generator {

	// http://groups.google.com/group/Google-Web-Toolkit/msg/ae249ea67c2c3435

	/** Simple name of class to be generated */
	private String className = null;

	/** Package name of class to be generated */
	private String packageName = null;

	/** Fully qualified class name passed into GWT.create() */
	private String typeName = null;

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
			className = classType.getSimpleSourceName() + "Wrapper";
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
			composer
					.setSuperclass("org.gwtapp.core.client.data.HashIterableModelData");
		} else {
			composer.setSuperclass(typeName);
		}
		SourceWriter sourceWriter = null;
		sourceWriter = composer.createSourceWriter(context, printWriter);
		// generator constructor source code
		generateConstructor(sourceWriter);
		if (Class.forName(typeName).isInterface()) {
			Set<String> properties = new HashSet<String>();
			for (Method method : Class.forName(typeName).getMethods()) {
				generateMethod(sourceWriter, properties, method);
			}
			generateMethodGetPropertyNames(sourceWriter, properties);
		}
		// close generated class
		sourceWriter.outdent();
		sourceWriter.println("}");

		// commit generated class
		context.commit(logger, printWriter);
	}

	private void generateConstructor(SourceWriter sourceWriter) {
		// start constructor source generation
		sourceWriter.println("public " + className + "() { ");
		sourceWriter.indent();
		sourceWriter.println("super();");
		// end constructor source generation
		sourceWriter.outdent();
		sourceWriter.println("}");
	}

	private String getPropertyName(String methodName) {
		String property = methodName.substring(3);
		return property.substring(0, 1).toLowerCase() + property.substring(1);
	}

	private void generateMethod(SourceWriter sourceWriter,
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

	private void generateMethodGetPropertyNames(SourceWriter sourceWriter,
			Set<String> properties) {
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
				.println("public java.util.Collection<java.lang.String> getPropertyNames() {");
		sourceWriter
				.println("return java.util.Arrays.asList(new java.lang.String[]{"
						+ s + "});");
		sourceWriter.println("}");
	}

	private void generateMethodGet(SourceWriter sourceWriter, Method method) {
		String name = method.getName();
		String type = method.getReturnType().getName();
		sourceWriter.println("public " + type + " " + name + "() { ");
		sourceWriter.indent();
		sourceWriter.println("return (" + type + ") get(\""
				+ getPropertyName(name) + "\");");
		sourceWriter.outdent();
		sourceWriter.println("}");
	}

	private void generateMethodSet(SourceWriter sourceWriter, Method method) {
		String name = method.getName();
		String type = method.getParameterTypes()[0].getName();
		sourceWriter.println("public void " + name + "(" + type + " value) { ");
		sourceWriter.indent();
		sourceWriter.println("set(\"" + getPropertyName(name) + "\", value);");
		sourceWriter.outdent();
		sourceWriter.println("}");
	}
}
