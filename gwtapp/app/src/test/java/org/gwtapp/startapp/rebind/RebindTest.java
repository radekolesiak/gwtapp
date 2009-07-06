package org.gwtapp.startapp.rebind;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URI;
import java.util.Arrays;
import java.util.HashMap;

import javax.tools.FileObject;
import javax.tools.ForwardingJavaFileManager;
import javax.tools.JavaCompiler;
import javax.tools.JavaFileManager;
import javax.tools.JavaFileObject;
import javax.tools.SimpleJavaFileObject;
import javax.tools.StandardLocation;
import javax.tools.ToolProvider;
import javax.tools.JavaCompiler.CompilationTask;
import javax.tools.JavaFileObject.Kind;

import org.junit.Assert;
import org.junit.Test;

import com.helionprime.jruntime.RuntimeClass;
import com.helionprime.jruntime.RuntimeClassException;

public class RebindTest {

	// http://www.rgagnon.com/javadetails/java-0039.html
	// http://www.ibm.com/developerworks/java/library/j-jcomp/index.html
	// http://www.java2s.com/Tutorial/Java/0120__Development/CompilingfromMemory.
	// http://opensource.helion-prime.com/jruntime/ & https://sourceforge.net/projects/jruntime/files/

	@Test
	public void runtimeClassLowLevelTest() {
		class JavaMemFileManager extends ForwardingJavaFileManager {

			class ClassMemFileObject extends SimpleJavaFileObject {
				ByteArrayOutputStream os = new ByteArrayOutputStream();

				ClassMemFileObject(String className) {
					super(URI.create("mem:///" + className
							+ Kind.CLASS.extension), Kind.CLASS);
				}

				byte[] getBytes() {
					return os.toByteArray();
				}

				@Override
				public OutputStream openOutputStream() throws IOException {
					return os;
				}
			}

			private HashMap<String, ClassMemFileObject> classes = new HashMap<String, ClassMemFileObject>();

			public JavaMemFileManager() {
				super(ToolProvider.getSystemJavaCompiler()
						.getStandardFileManager(null, null, null));
			}

			@Override
			public JavaFileObject getJavaFileForOutput(Location location,
					String className, Kind kind, FileObject sibling)
					throws IOException {
				if (StandardLocation.CLASS_OUTPUT == location
						&& JavaFileObject.Kind.CLASS == kind) {
					ClassMemFileObject clazz = new ClassMemFileObject(className);
					classes.put(className, clazz);
					return clazz;
				} else {
					return super.getJavaFileForOutput(location, className,
							kind, sibling);
				}
			}

			public byte[] getClassBytes(String className) {
				if (classes.containsKey(className)) {
					return classes.get(className).getBytes();
				}
				return null;
			}
		}

		class JavaSourceFromString extends SimpleJavaFileObject {
			final String code;

			JavaSourceFromString(String name, String code) {
				super(URI.create("string:///" + name.replace('.', '/')
						+ Kind.SOURCE.extension), Kind.SOURCE);
				this.code = code;
			}

			@Override
			public CharSequence getCharContent(boolean ignoreEncodingErrors) {
				return code;
			}
		}
		String code = //
		"public class TestClass {\n"//
				+ " public TestClass(){}\n"//
				+ "	public static void main(String[] args) {\n"//
				+ "		System.out.println(\"Hello World!\");\n"// 
				+ "	}\n" //
				+ "}\n";

		JavaSourceFromString source = new JavaSourceFromString("TestClass",
				code);
		Iterable<? extends JavaFileObject> sources = Arrays.asList(source);
		JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
		JavaFileManager fileManager = new JavaMemFileManager();
		CompilationTask task = compiler.getTask(null, fileManager, null, null,
				null, sources);
		Assert.assertTrue(task.call());
		byte[] myClassBytes = ((JavaMemFileManager) fileManager)
				.getClassBytes("TestClass");
	}

	@Test
	public void runtimeClassHighLevelTest() throws ClassNotFoundException, RuntimeClassException, InstantiationException, IllegalAccessException {
		RuntimeClass myFirstRuntimeClass = new RuntimeClass(
				"public class MyRuntimeClass{" +
				"public String toString() {return \"-- MyRuntimeClass --\";}" +
				"}");
		Object instance = myFirstRuntimeClass.newInstance();
		Assert.assertEquals("-- MyRuntimeClass --", instance.toString());
	}
}
