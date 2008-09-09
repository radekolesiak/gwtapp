package com.cuprum.web.smallapp.mainapp.client.page;

import com.cuprum.web.common.client.data.TConnectionSession;
import com.cuprum.web.common.client.data.TUserSession;
import com.cuprum.web.smallapp.mainapp.client.i18n.MainAppMessages;
import com.cuprum.web.templates.simple.client.Simple;
import com.cuprum.web.widgets.common.client.SubmitListener;
import com.cuprum.web.widgets.user.login.client.LoginListener;
import com.cuprum.web.widgets.user.login.client.UserLogin;
import com.cuprum.web.widgets.user.register.client.UserRegister;
import com.cuprum.web.widgets.user.register.client.data.TUserRegisterValue;
import com.extjs.gxt.ui.client.widget.form.LabelField;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class WelcomePage extends VerticalPanel {
	public WelcomePage() {
		RootPanel.get().addStyleName("smallapp");

		MainAppMessages messages = GWT.create(MainAppMessages.class);

		Window.setTitle("SmallApp :: " + TConnectionSession.getSession().get()
				+ " :: " + messages.msgHelloWorld());

		final UserRegister userRegister = new UserRegister();
		final UserLogin userLogin = new UserLogin();

		userLogin.loginListeners.add(new LoginListener() {
			public void onLogin(Widget widget, TUserSession session) {
				TUserSession.setSession(session);
				add(new Label("Session = " + session.get()));
				WorkspacePage.setAsCurrent();
			}
		});

		userRegister.addSubmitListener(new SubmitListener() {
			public void onSubmit(Widget sender) {
				userRegister.setUserRegister(new TUserRegisterValue());
				add(new LabelField(
						"Account has benn created. Receive email and click on link to confirm registration."));

			}
		});

		add(userRegister);
		add(userLogin);
	}

	public static void setAsCurrent() {
		new Simple().run().setContent(new WelcomePage());
	}
}
