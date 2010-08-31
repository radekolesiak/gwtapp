package org.gwtapp.template.client.callback;

import java.util.Map;

import org.gwtapp.template.client.Template;
import org.gwtapp.template.client.TemplateHandler;
import org.gwtapp.template.client.ui.TemplatePanel;
import org.gwtapp.template.client.ui.TemplatePanel.TemplateCallback;

public class SimpleTemplateCallback implements TemplateCallback {

	private final Template template = new Template();

	@Override
	public void template(TemplatePanel<?> owner,
			Map<String, TemplateHandler> widgetHandlers) {
	}

	@Override
	public Template getTemplate() {
		return template;
	}
}
