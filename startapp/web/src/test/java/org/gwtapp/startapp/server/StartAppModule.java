package org.gwtapp.startapp.server;

import org.gwtapp.extension.user.client.data.ReCaptchaUser;
import org.gwtapp.extension.user.client.data.ReCaptchaUserImpl;
import org.gwtapp.extension.user.client.data.User;
import org.gwtapp.extension.user.client.data.UserImpl;
import org.gwtapp.extension.user.client.data.UserPassword;
import org.gwtapp.extension.user.client.data.UserPasswordImpl;
import org.gwtapp.extension.user.server.remote.ReCaptchaUserRemote;
import org.gwtapp.extension.user.server.stub.UserAddStub;
import org.gwtapp.startapp.server.service.UserServiceImpl;
import org.gwtapp.startapp.server.servlet.ValidationCssServlet;

import com.google.inject.servlet.ServletModule;
import com.wideplay.warp.jpa.JpaUnit;
import com.wideplay.warp.persist.PersistenceService;
import com.wideplay.warp.persist.UnitOfWork;

public class StartAppModule extends ServletModule {

	@Override
	protected void configureServlets() {
		setupTransactional();
		setupServlets();
		setupServices();
		setupModels();
	}

	private void setupTransactional() {
		install(PersistenceService.usingJpa().across(UnitOfWork.TRANSACTION)
				.buildModule());
		bindConstant().annotatedWith(JpaUnit.class).to("derby");
	}

	private void setupServlets() {
		serve("/gwt.startappmanualtestentry/extension.recaptchauser.rpc").with(
				ReCaptchaUserRemote.class);
		serve("/validation.css").with(ValidationCssServlet.class);
	}

	private void setupServices() {
		bind(UserAddStub.class).to(UserServiceImpl.class);
		bind(ReCaptchaUser.class).to(ReCaptchaUserImpl.class);
	}

	private void setupModels() {
		bind(User.class).to(UserImpl.class);
		bind(UserPassword.class).to(UserPasswordImpl.class);
	}
}
