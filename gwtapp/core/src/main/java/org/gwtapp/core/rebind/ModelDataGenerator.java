package org.gwtapp.core.rebind;

import java.io.PrintWriter;

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
			className = classType.getSimpleSourceName()
					+ Util.CLASS_NAME_SUFFIX;
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
			composer.setSuperclass(Util.SUPER_CLASS);
		} else {
			composer.setSuperclass(typeName);
		}
		SourceWriter sourceWriter = null;
		sourceWriter = composer.createSourceWriter(context, printWriter);
		System.out.println(Util.generateContent(typeName, className));
		sourceWriter.println(Util.generateContent(typeName, className));
		// close generated class
		sourceWriter.outdent();
		sourceWriter.println("}");

		// commit generated class
		context.commit(logger, printWriter);
	}

}
