package org.gwtapp.core.server;

import java.lang.annotation.Annotation;
import java.util.List;

import org.gwtapp.core.rpc.exception.Validation;
import org.gwtapp.core.rpc.exception.ValidationField;
import org.junit.Assert;
import org.junit.Test;

public class ValidationCssTest {

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
		Assert.assertEquals(3, classes.length);
		Assert.assertEquals(ValidationTestException.Login.class, classes[1]);
		Assert.assertEquals(ValidationTestException.Email.class, classes[0]);
		Assert.assertEquals(ValidationTestException.Field.class, classes[2]);
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
		ValidationCssGenerator generator = new ValidationCssGenerator();
		generator.setValidationClass(ValidationTestException.class);
		List<Class<?>> classes = generator.getAnnotatedSubclasses();
		Assert.assertNotNull(classes);
		Assert.assertEquals(2, classes.size());
	}

	@Test
	public void testValidationCssGeneratorGetEnumConstants() {
		ValidationCssGenerator generator = new ValidationCssGenerator();
		generator.setValidationClass(ValidationTestException.class);
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
	public void testValidationName() {
		ValidationCssGenerator generator = new ValidationCssGenerator();
		generator.setValidationClass(ValidationTestException.class);
		Assert.assertEquals("user-validation", generator.getValidationName());
	}

	@Test
	public void testValidationFieldName() {
		ValidationCssGenerator generator = new ValidationCssGenerator();
		generator.setValidationClass(ValidationTestException.class);
		List<Class<?>> classes = generator.getAnnotatedSubclasses();
		Assert.assertNotNull(classes);
		Assert.assertEquals("login",
				generator.getValidationFieldName(classes.get(1)));
	}

	@Test
	public void testEnumConstantFieldCssGenerator() {
		ValidationCssGenerator generator = new ValidationCssGenerator();
		generator.setValidationClass(ValidationTestException.class);
		List<Class<?>> classes = generator.getAnnotatedSubclasses();
		List<Enum<?>> constants = generator.getEnumConstants(classes.get(1));
		{
			StringBuilder s = new StringBuilder();
			generator.getCssForMatcherAndEnumConstant(s, "aba",
					generator.getValidationFieldName(classes.get(1)),
					constants.get(0));
			Assert.assertEquals(
					".aba .user-validation .validation-login-valid .validation.login-valid { display: block; }\n",
					s.toString());
		}
		{
			StringBuilder s = new StringBuilder();
			generator.getCssForMatcherAndEnumConstant(s, "aba",
					generator.getValidationFieldName(classes.get(1)),
					constants.get(1));
			Assert.assertEquals(
					".aba .user-validation .validation-login-invalid .validation.login-invalid { display: block; }\n",
					s.toString());
		}
	}

	@Test
	public void testEnumCssGenerator() {
		ValidationCssGenerator generator = new ValidationCssGenerator();
		generator.setValidationClass(ValidationTestException.class);
		List<Class<?>> classes = generator.getAnnotatedSubclasses();
		StringBuilder s = new StringBuilder();
		generator.getCssForMatcherAndEnum(s, "aba", classes.get(1));
		Assert.assertEquals(
				".aba .user-validation .validation-login-valid .validation.login-valid { display: block; }\n"
						+ ".aba .user-validation .validation-login-invalid .validation.login-invalid { display: block; }\n"
						+ ".aba .user-validation .validation-login-too-short .validation.login-too-short { display: block; }\n"
						+ ".aba .user-validation .validation-login-not-letters-only .validation.login-not-letters-only { display: block; }\n"
						+ ".aba .user-validation .validation-login-not-lower-case .validation.login-not-lower-case { display: block; }\n"
						+ ".aba .user-validation .validation-login-already-exists .validation.login-already-exists { display: block; }\n",
				s.toString());
	}
}
