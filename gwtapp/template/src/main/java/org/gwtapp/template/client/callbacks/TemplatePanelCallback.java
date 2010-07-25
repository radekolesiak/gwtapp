package org.gwtapp.template.client.callbacks;

import java.util.Map;

import org.gwtapp.template.client.TemplateHandler;
import org.gwtapp.template.client.ui.TemplatePanel;

public interface TemplatePanelCallback {
	void template(TemplatePanel<?> owner,
			Map<String, TemplateHandler> widgetHandlers);
}
