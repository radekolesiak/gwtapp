package org.gwtapp.startapp.server;

import org.gwtapp.extension.user.server.remote.ReCaptchaUserRemote;
import org.gwtapp.extension.user.server.stub.UserAddStub;
import org.gwtapp.startapp.server.service.UserServiceImpl;

import com.google.inject.servlet.ServletModule;

public class StartAppModule extends ServletModule {
	@Override
	protected void configureServlets() {
		serve("/gwt.startappmanualtestentry/extension.recaptchauser.rpc").with(
				ReCaptchaUserRemote.class);
		bind(UserAddStub.class).to(UserServiceImpl.class);
	}
}
