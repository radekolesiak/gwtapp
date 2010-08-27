package org.gwtapp.startapp.client;

import org.gwtapp.core.client.AsyncCallbackInjector;
import org.gwtapp.extension.user.client.ReCaptchaUserRegisterServicePanel;
import org.gwtapp.extension.user.client.data.ReCaptchaUser;
import org.gwtapp.extension.user.client.data.ReCaptchaUserImpl;
import org.gwtapp.extension.user.client.data.User;
import org.gwtapp.extension.user.client.data.UserImpl;
import org.gwtapp.extension.user.client.data.UserPassword;
import org.gwtapp.extension.user.client.data.UserPasswordImpl;
import org.gwtapp.startapp.client.template.ReCaptchaUserRegisterTemplateCallback;
import org.gwtapp.template.client.ui.TemplatePanel.TemplateCallback;

import com.google.gwt.inject.client.AbstractGinModule;
import com.google.inject.name.Names;

public class ClientModule extends AbstractGinModule {
	@Override
	protected void configure() {
		bind(AsyncCallbackInjector.class)
				.to(AsyncCallbackInjectorAdapter.class);
		bind(User.class).to(UserImpl.class);
		bind(UserPassword.class).to(UserPasswordImpl.class);
		bind(ReCaptchaUser.class).to(ReCaptchaUserImpl.class);
		bind(TemplateCallback.class).annotatedWith(
				Names.named(ReCaptchaUserRegisterServicePanel.BIND_NAME)).to(
				ReCaptchaUserRegisterTemplateCallback.class);
	}
}
