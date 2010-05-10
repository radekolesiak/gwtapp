package org.gwtapp.template.client;

import org.junit.Test;

public class GwtTestReplaceParameters extends TemplateTest {

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

	@Test
	public void testReplaceParametersByName1() {
		assertEquals("AXB", TemplateUtils.replaceParameters("A{1}B", new Param(
				"1", "X")));
	}

	@Test
	public void testReplaceParametersByName2() {
		assertEquals("AXB{2}", TemplateUtils.replaceParameters("A{1}B{2}", new Param(
				"1", "X")));
	}

	@Test
	public void testReplaceParametersByName3() {
		assertEquals("AXB", TemplateUtils.replaceParameters("A{n-a-m-e_1}B", new Param(
				"n-a-m-e_1", "X")));
	}
}
