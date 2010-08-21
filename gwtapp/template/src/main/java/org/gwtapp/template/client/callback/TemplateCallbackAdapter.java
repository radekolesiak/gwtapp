package org.gwtapp.template.client.callback;

import java.util.Map;

import org.gwtapp.template.client.Template;
import org.gwtapp.template.client.TemplateHandler;
import org.gwtapp.template.client.ui.TemplatePanel;
import org.gwtapp.template.client.ui.TemplatePanel.TemplateCallback;

public class TemplateCallbackAdapter implements TemplateCallback {

	private final TemplateCallback callback;

	public TemplateCallbackAdapter(TemplateCallback callback) {
		this.callback = callback;
	}

	@Override
	public Template getTemplate() {
		return callback.getTemplate();
	}

	@Override
	public void template(TemplatePanel<?> owner,
			Map<String, TemplateHandler> widgetHandlers) {
		callback.template(owner, widgetHandlers);
	}
}
