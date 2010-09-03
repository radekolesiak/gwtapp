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
		Assert.assertEquals(2, classes.length);
		Assert.assertEquals(ValidationTestException.Login.class, classes[0]);
		Assert.assertEquals(ValidationTestException.Field.class, classes[1]);
		Assert.assertNotNull(ValidationTestException.Login.class
				.getAnnotation(ValidationField.class));
		Assert.assertNotNull(classes[0].getAnnotation(ValidationField.class));
		Object[] enums = classes[0].getEnumConstants();
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
		Assert.assertEquals(1, classes.size());
	}
}
