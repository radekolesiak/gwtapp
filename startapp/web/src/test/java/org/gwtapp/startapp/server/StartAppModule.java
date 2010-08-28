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
import org.gwtapp.extension.user.server.remote.ReCaptchaUserRemote;
import org.gwtapp.extension.user.server.stub.UserAddStub;
import org.gwtapp.startapp.server.service.UserServiceImpl;

import com.google.inject.Provider;
import com.google.inject.servlet.ServletModule;

public class StartAppModule extends ServletModule {

	static class EntityManagerProvider implements Provider<EntityManager> {

		private final static EntityManagerFactory emf = Persistence
				.createEntityManagerFactory("derby");

		public EntityManager get() {
			return emf.createEntityManager();
		}
	}

	@Override
	protected void configureServlets() {
		serve("/gwt.startappmanualtestentry/extension.recaptchauser.rpc").with(
				ReCaptchaUserRemote.class);
		bind(UserAddStub.class).to(UserServiceImpl.class);
		bind(User.class).to(UserImpl.class);
		bind(UserPassword.class).to(UserPasswordImpl.class);
		bind(ReCaptchaUser.class).to(ReCaptchaUserImpl.class);
		bind(EntityManager.class).toProvider(EntityManagerProvider.class);
	}
}
