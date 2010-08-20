package org.gwtapp.startapp.client;

import org.gwtapp.core.client.AsyncCallbackInjector;
import org.gwtapp.extension.user.client.ReCaptchaUserRegisterServicePanel;

import com.google.gwt.inject.client.AbstractGinModule;

public class ClientModule extends AbstractGinModule {
	@Override
	protected void configure() {
		bind(ReCaptchaUserRegisterServicePanel.Injection.class).to(
				ReCaptchaUserRegisterPanelServiceInjection.class);
		bind(AsyncCallbackInjector.class)
				.to(AsyncCallbackInjectorAdapter.class);
	}
}
