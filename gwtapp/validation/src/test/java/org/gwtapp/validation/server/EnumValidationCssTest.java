package org.gwtapp.validation.server;

import java.lang.annotation.Annotation;
import java.util.List;

import org.gwtapp.validation.rpc.exception.Validation;
import org.gwtapp.validation.rpc.exception.ValidationField;
import org.gwtapp.validation.server.EnumValidationCssGenerator;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class EnumValidationCssTest {

	private EnumValidationCssGenerator generator;

	@Before
	public void tearUp() {
		generator = new EnumValidationCssGenerator();
		generator.setValidationClass(ValidationTestException.class);
		generator.setPrefix(".user-panel");
		generator.setSeparator(".validation.");
		generator.setStyle("display: block;");
	}

	@Test
	public void testAnnotation() throws InstantiationException,
			IllegalAccessException {
		Assert.assertNotNull(ValidationTestException.class
				.getAnnotation(Validation.class));
		Annotation[] annotations = ValidationTestException.class
				.getAnnotations();
		Assert.assertNotNull(annotations);
		Assert.assertEquals(1, annotations.length);
		Assert.assertNotNull(annotations[0]);
		Assert.assertTrue(annotations[0] instanceof Validation);
		Class<?>[] classes = ValidationTestException.class.getClasses();
		Assert.assertNotNull(classes);
		Assert.assertEquals(2, classes.length);
		Assert.assertEquals(ValidationTestException.Login.class, classes[1]);
		Assert.assertEquals(ValidationTestException.Email.class, classes[0]);
		Assert.assertNotNull(ValidationTestException.Login.class
				.getAnnotation(ValidationField.class));
		Assert.assertNotNull(classes[0].getAnnotation(ValidationField.class));
		Assert.assertNotNull(classes[1].getAnnotation(ValidationField.class));
		Object[] enums = classes[1].getEnumConstants();
		Assert.assertNotNull(enums);
		Assert.assertEquals(6, enums.length);
		Assert.assertEquals("VALID", ((Enum<?>) enums[0]).name());
	}

	@Test
	public void testValidationCssGeneratorGetAnnotatedClasses() {
		List<Class<?>> classes = generator.getAnnotatedSubclasses();
		Assert.assertNotNull(classes);
		Assert.assertEquals(2, classes.size());
	}

	@Test
	public void testValidationCssGeneratorGetEnumConstants() {
		List<Class<?>> classes = generator.getAnnotatedSubclasses();
		Assert.assertNotNull(classes);
		Assert.assertEquals(2, classes.size());
		List<Enum<?>> constants = generator.getEnumConstants(classes.get(1));
		Assert.assertNotNull(constants);
		Assert.assertEquals(6, constants.size());
		for (int i = 0; i < constants.size(); i++) {
			Assert.assertEquals(ValidationTestException.Login.values()[i],
					constants.get(i));
		}
	}

	@Test
	public void testValidationFieldName() {
		List<Class<?>> classes = generator.getAnnotatedSubclasses();
		Assert.assertNotNull(classes);
		Assert.assertEquals("login",
				generator.getValidationFieldValue(classes.get(1)));
	}

	@Test
	public void testEnumConstantFieldCssGenerator() {
		List<Class<?>> classes = generator.getAnnotatedSubclasses();
		List<Enum<?>> constants = generator.getEnumConstants(classes.get(1));
		{
			StringBuilder s = new StringBuilder();
			generator.getCssForEnumConstant(s,
					generator.getValidationFieldValue(classes.get(1)),
					constants.get(0));
			Assert.assertEquals(
					".user-panel .validation-login-valid .validation.login-valid { display: block; }\n",
					s.toString());
		}
		{
			StringBuilder s = new StringBuilder();
			generator.getCssForEnumConstant(s,
					generator.getValidationFieldValue(classes.get(1)),
					constants.get(1));
			Assert.assertEquals(
					".user-panel .validation-login-invalid .validation.login-invalid { display: block; }\n",
					s.toString());
		}
	}

	@Test
	public void testEnumCssGenerator() {
		List<Class<?>> classes = generator.getAnnotatedSubclasses();
		StringBuilder s = new StringBuilder();
		generator.getCssForEnum(s, classes.get(1));
		Assert.assertEquals(
				""//
						+ ".user-panel .validation-login-valid .validation.login-valid { display: block; }\n"
						+ ".user-panel .validation-login-invalid .validation.login-invalid { display: block; }\n"
						+ ".user-panel .validation-login-too-short .validation.login-too-short { display: block; }\n"
						+ ".user-panel .validation-login-not-letters-only .validation.login-not-letters-only { display: block; }\n"
						+ ".user-panel .validation-login-not-lower-case .validation.login-not-lower-case { display: block; }\n"
						+ ".user-panel .validation-login-already-exists .validation.login-already-exists { display: block; }\n",
				s.toString());
	}

	@Test
	public void testCssGenerator() {
		Assert.assertEquals(
				""//
						+ ".user-panel .validation-email-valid .validation.email-valid { display: block; }\n"
						+ ".user-panel .validation-email-invalid .validation.email-invalid { display: block; }\n"
						+ ".user-panel .validation-email-already-exists .validation.email-already-exists { display: block; }\n"
						+ ".user-panel .validation-login-valid .validation.login-valid { display: block; }\n"
						+ ".user-panel .validation-login-invalid .validation.login-invalid { display: block; }\n"
						+ ".user-panel .validation-login-too-short .validation.login-too-short { display: block; }\n"
						+ ".user-panel .validation-login-not-letters-only .validation.login-not-letters-only { display: block; }\n"
						+ ".user-panel .validation-login-not-lower-case .validation.login-not-lower-case { display: block; }\n"
						+ ".user-panel .validation-login-already-exists .validation.login-already-exists { display: block; }\n",
				generator.getCSS());
	}
}
