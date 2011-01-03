package org.gwtapp.template.client.callback;

import java.util.Map;

import org.gwtapp.template.client.Template;
import org.gwtapp.template.client.handler.TemplateHandler;
import org.gwtapp.template.client.ui.TemplatePanel;
import org.gwtapp.template.client.ui.TemplatePanel.TemplateCallback;

public class SimpleTemplateCallback implements TemplateCallback {

	private final TFieldCallback callback = new TFieldCallback();
	private final Template template;

	public SimpleTemplateCallback() {
		this(new Template());
	}

	public SimpleTemplateCallback(Template template) {
		this.template = template;
	}

	@Override
	public void template(TemplatePanel<?> owner,
			Map<String, TemplateHandler> widgetHandlers) {
		callback.template(owner, widgetHandlers);
	}

	@Override
	public Template getTemplate() {
		return template;
	}

	public void setPattern(String pattern) {
		callback.setPattern(pattern);
	}

	public String getPattern() {
		return callback.getPattern();
	}
}
