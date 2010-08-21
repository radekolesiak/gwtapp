package org.gwtapp.startapp.client;

import org.gwtapp.template.client.callback.TemplateCallbackAdapter;

public class ReCaptchaUserRegisterTemplateCallback extends
		TemplateCallbackAdapter {
	public ReCaptchaUserRegisterTemplateCallback() {
		super(StartAppManualTestEntryPoint.template
				.load("user/ReCaptchaUserPanel.jsp"));
	}
}
