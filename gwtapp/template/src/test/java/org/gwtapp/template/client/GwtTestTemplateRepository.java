package org.gwtapp.template.client;

import org.junit.Test;

public class GwtTestTemplateRepository extends TemplateTest {

	@Test
	public void testInitState() {
		TemplateRepository repository = new TemplateRepository();
		assertEquals("/", repository.getRepository());
		assertEquals(TemplateRepository.Type.TFIELD, repository
				.getDefaultType());
	}
}