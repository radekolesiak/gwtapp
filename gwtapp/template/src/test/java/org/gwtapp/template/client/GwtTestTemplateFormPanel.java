package org.gwtapp.template.client;

import java.util.Map;

import org.gwtapp.template.client.ui.TemplateFormPanel;
import org.gwtapp.template.client.ui.TemplatePanel;
import org.junit.Test;

public class GwtTestTemplateFormPanel extends TemplateTest {

	private class CustomTemplateCallback implements
			TemplatePanel.TemplateCallback {

		private final Template template;

		public CustomTemplateCallback() {
			this(new Template());
		}

		public CustomTemplateCallback(Template template) {
			this.template = template;
		}

		@Override
		public Template getTemplate() {
			return template;
		}

		@Override
		public void template(TemplatePanel<?> owner,
				Map<String, TemplateHandler> widgetHandlers) {
			for (TemplateHandler handler : widgetHandlers.values()) {
				owner.add(handler.onWidget(null));
			}
		}
	}

	@Test
	public void test() {
		TemplateFormPanel<Void> panel = new TemplateFormPanel<Void>(
				new CustomTemplateCallback());
		assertEquals("template-panel template-form-panel", panel.getStyleName());
	}
}
