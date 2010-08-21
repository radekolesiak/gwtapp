package org.gwtapp.startapp.client;

import java.util.Map;

import org.gwtapp.template.client.Template;
import org.gwtapp.template.client.TemplateHandler;
import org.gwtapp.template.client.ui.TemplatePanel;
import org.gwtapp.template.client.ui.TemplatePanel.TemplateCallback;

public class ReCaptchaUserRegisterCallback implements TemplateCallback {

	TemplateCallback callback = StartAppManualTestEntryPoint.template
			.load("user/ReCaptchaUserPanel.jsp");

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
