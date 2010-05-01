package org.gwtapp.startapp.client;

import org.gwtapp.template.client.TemplateUtils;
import org.junit.Test;

public class TemplateUtilsTest extends StartAppEntryPointTest {

	@Test
	public void testReplaceParameters1() {
		assertEquals("AXB", TemplateUtils.replaceParameters("A{1}B", "X"));
	}

	@Test
	public void testReplaceParameters2() {
		assertEquals("A{0}B", TemplateUtils.replaceParameters("A{0}B", "X"));
	}

	@Test
	public void testReplaceParameters3() {
		assertEquals("A{-1}B", TemplateUtils.replaceParameters("A{-1}B", "X"));
	}

	@Test
	public void testReplaceParameters4() {
		assertEquals("AYBXC", TemplateUtils.replaceParameters("A{2}B{1}C", "X",
				"Y"));
	}

	@Test
	public void testReplaceParameters5() {
		assertEquals("AYBXC", TemplateUtils.replaceParameters("A{11}B{10}C",
				"a", "b", "c", "d", "e", "f", "g", "h", "i", "X", "Y"));
	}

	@Test
	public void testReplaceParameters6() {
		assertEquals("AYB", TemplateUtils.replaceParameters("A{2}B", "X", "Y"));
	}

	@Test
	public void testReplaceParameters7() {
		assertEquals("AZB", TemplateUtils.replaceParameters("A{3}B", "X", "Y",
				"Z"));
	}
}
