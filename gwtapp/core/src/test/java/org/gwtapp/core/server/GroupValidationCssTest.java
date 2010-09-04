package org.gwtapp.core.server;

import java.util.List;

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
						+ ".user-panel .validation-login .validation.login { background-color: orange; }\n",
				generator.getCSS());
	}
}
