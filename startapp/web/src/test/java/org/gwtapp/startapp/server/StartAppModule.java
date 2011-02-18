package org.gwtapp.startapp.server;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.gwtapp.extension.user.client.data.ReCaptchaUser;
import org.gwtapp.extension.user.client.data.ReCaptchaUserImpl;
import org.gwtapp.extension.user.client.data.User;
import org.gwtapp.extension.user.client.data.UserImpl;
import org.gwtapp.extension.user.client.data.UserPassword;
import org.gwtapp.extension.user.client.data.UserPasswordImpl;
import org.gwtapp.extension.user.server.local.service.ReCaptchaVerifyImpl;
import org.gwtapp.extension.user.server.local.stub.ReCaptchaPrivateKey;
import org.gwtapp.extension.user.server.local.stub.ReCaptchaVerify;
import org.gwtapp.extension.user.server.local.stub.UserAdd;
import org.gwtapp.extension.user.server.remote.service.ReCaptchaUserRemoteService;
import org.gwtapp.startapp.server.service.UserServiceImpl;
import org.gwtapp.startapp.server.servlet.EnumValidationCssServlet;
import org.gwtapp.startapp.server.servlet.GroupValidationCssServlet;

import com.google.inject.Provider;
import com.google.inject.servlet.ServletModule;

public class StartAppModule extends ServletModule {

	private static EntityManagerFactory emf;
	private static EntityManager em;

	@Override
	protected void configureServlets() {
		setupTransactional();
		setupServlets();
		setupServices();
		setupModels();
	}

	private void setupTransactional() {
		emf = Persistence
				.createEntityManagerFactory("derby-startapp-manual-test-x");
		em = emf.createEntityManager();
		bind(EntityManager.class).toProvider(EntityManagerProvider.class);
	}

	private void setupServlets() {
		serve("/gwt.startappmanualtestentry/extension.recaptchauser.rpc").with(
				ReCaptchaUserRemoteService.class);
		serve("/css/validation-by-group.css").with(
				GroupValidationCssServlet.class);
		serve("/css/validation-by-enum.css").with(
				EnumValidationCssServlet.class);
	}

	private void setupServices() {
		bind(UserAdd.class).to(UserServiceImpl.class);
		bind(ReCaptchaUser.class).to(ReCaptchaUserImpl.class);
		bind(ReCaptchaVerify.class).to(ReCaptchaVerifyImpl.class);
		bind(ReCaptchaPrivateKey.class).to(ReCaptchaPrivateKeyProvider.class);
	}

	private void setupModels() {
		bind(User.class).to(UserImpl.class);
		bind(UserPassword.class).to(UserPasswordImpl.class);
	}

	private static class ReCaptchaPrivateKeyProvider implements
			ReCaptchaPrivateKey {
		@Override
		public String getPrivateKey() {
			return "6LexqMESAAAAANbEWWSLTo49JIVrQQ8NEB32ug-i";
		}
	}

	private static class EntityManagerProvider implements
			Provider<EntityManager> {
		@Override
		public EntityManager get() {
			return em;
		}
	}
}
