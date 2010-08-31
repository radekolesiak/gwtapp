package org.gwtapp.startapp.client.template;

import org.gwtapp.startapp.client.StartAppManualTestEntryPoint;
import org.gwtapp.template.client.callback.TemplateCallbackAdapter;

public class ReCaptchaUserRegisterTemplateCallback extends
		TemplateCallbackAdapter {
	public ReCaptchaUserRegisterTemplateCallback() {
		super(StartAppManualTestEntryPoint.template
				.load("user/ReCaptchaUserPanel.jsp"));
	}
}
