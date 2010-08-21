package org.gwtapp.startapp.client;

import org.gwtapp.core.client.AsyncCallbackInjector;
import org.gwtapp.extension.user.client.data.ReCaptchaUser;
import org.gwtapp.extension.user.client.data.ReCaptchaUserImpl;
import org.gwtapp.template.client.ui.TemplatePanel.TemplateCallback;

import com.google.gwt.inject.client.AbstractGinModule;

public class ClientModule extends AbstractGinModule {

	@Override
	protected void configure() {
		bind(AsyncCallbackInjector.class)
		.to(AsyncCallbackInjectorAdapter.class);
		bind(ReCaptchaUser.class).to(ReCaptchaUserImpl.class);
		bind(TemplateCallback.class).to(ReCaptchaUserRegisterCallback.class);
	}
}
