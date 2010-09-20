package org.gwtapp.validation.server;

import java.util.List;
import java.util.Map;

import org.gwtapp.validation.rpc.exception.ValidationException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class GroupValidationCssTest {

	private GroupValidationCssGenerator generator;

	@Before
	public void tearUp() {
		generator = new GroupValidationCssGenerator();
		generator.setValidationClass(ValidationTestException.class);
		generator.setPrefix(".user-panel");
		generator.setSeparator(".validation.");
		generator.setStyle("background-color: orange;");
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
		{
			StringBuilder s = new StringBuilder();
			generator.getCssForGroup(s, classes.get(1));
			Assert.assertEquals(
					".user-panel .validation-login .validation.login { background-color: orange; }\n",
					s.toString());
		}
		{
			StringBuilder s = new StringBuilder();
			generator.getCssForGroup(s, classes.get(1));
			Assert.assertEquals(
					".user-panel .validation-login .validation.login { background-color: orange; }\n",
					s.toString());
		}
	}

	@Test
	public void testEnumCssGenerator() {
		List<Class<?>> classes = generator.getAnnotatedSubclasses();
		StringBuilder s = new StringBuilder();
		generator.getCssForGroup(s, classes.get(1));
		Assert.assertEquals(
				".user-panel .validation-login .validation.login { background-color: orange; }\n",
				s.toString());
	}

	@Test
	public void testCssGenerator() {
		Assert.assertEquals(
				""//
						+ ".user-panel .validation-email .validation.email { background-color: orange; }\n"
						+ ".user-panel .validation-login .validation.login { background-color: orange; }\n"
						+ ".user-panel .validation-subvalidation-password .validation.subvalidation-password { background-color: orange; }\n",
				generator.getCSS());
	}

	@Test
	public void testSubvalidationClasses() {
		Map<String, Class<? extends ValidationException>> subvalidation = generator
				.getChildrenFields();
		Assert.assertNotNull(subvalidation);
		Assert.assertEquals(1, subvalidation.size());
		Class<? extends ValidationException> c = subvalidation
				.get("subvalidation");

		GroupValidationCssGenerator subgenerator = new GroupValidationCssGenerator();
		subgenerator.setValidationClass(c);
		List<Class<?>> subclasses = subgenerator.getAnnotatedSubclasses();
		Assert.assertNotNull(subclasses);
		Assert.assertEquals(1, subclasses.size());
		Assert.assertEquals(SubValidationTestException.Password.class,
				subclasses.get(0));
		List<Enum<?>> enums = subgenerator.getEnumConstants(subclasses.get(0));
		Assert.assertNotNull(enums);
		Assert.assertEquals(1, enums.size());
		Assert.assertEquals(SubValidationTestException.Password.INVALID,
				enums.get(0));
	}
}
