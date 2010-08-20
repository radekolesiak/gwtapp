package org.gwtapp.startapp.client;

import org.gwtapp.core.client.AsyncCallbackInjector;
import org.gwtapp.extension.user.client.ReCaptchaUserRegisterServicePanel;
import org.gwtapp.template.client.ui.TemplatePanel.TemplateCallback;

import com.google.inject.Inject;

public class ReCaptchaUserRegisterPanelServiceInjection implements
		ReCaptchaUserRegisterServicePanel.Injection {

	private AsyncCallbackInjector injector;

	@Inject
	public ReCaptchaUserRegisterPanelServiceInjection(AsyncCallbackInjector injector) {
		this.injector = injector;
	}

	@Override
	public AsyncCallbackInjector getAsyncCallbackInjector() {
		return injector;
	}

	@Override
	public TemplateCallback getTemplateCallback() {
		return StartAppManualTestEntryPoint.template
				.load("user/ReCaptchaUserPanel.jsp");
	}
}
