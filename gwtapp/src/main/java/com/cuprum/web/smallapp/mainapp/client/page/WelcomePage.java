package com.cuprum.web.smallapp.mainapp.client.page;

import com.cuprum.web.common.client.data.TConnectionSession;
import com.cuprum.web.common.client.data.TUserSession;
import com.cuprum.web.smallapp.mainapp.client.i18n.MainAppMessages;
import com.cuprum.web.templates.simple.client.Simple;
import com.cuprum.web.widgets.common.client.SubmitListener;
import com.cuprum.web.widgets.user.login.client.UserLogin;
import com.cuprum.web.widgets.user.password.client.ChangePasswordByToken;
import com.cuprum.web.widgets.user.password.client.ChangePasswordGetToken;
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
	final UserRegister userRegister = new UserRegister();

	final UserLogin userLogin = new UserLogin();

	final ChangePasswordByToken changePasswordByToken = new ChangePasswordByToken();

	final ChangePasswordGetToken changePasswordGetToken = new ChangePasswordGetToken();

	public WelcomePage() {
		RootPanel.get().addStyleName("smallapp");

		MainAppMessages messages = GWT.create(MainAppMessages.class);

		Window.setTitle("SmallApp :: " + TConnectionSession.getSession().get()
				+ " :: " + messages.msgHelloWorld());

		userLogin.addSubmitListener(new SubmitListener() {
			public void onSubmit(Widget sender) {
				TUserSession.setSession(userLogin.getValue());
				add(new Label("Session = " + userLogin.getValue()));
				WorkspacePage.setAsCurrent();
			}
		});

		userRegister.addSubmitListener(new SubmitListener() {
			public void onSubmit(Widget sender) {
				userRegister.setValue(new TUserRegisterValue());
				add(new LabelField(
						"Account has been created. Receive email and click on link to confirm registration."));

			}
		});

		add(userRegister);
		add(userLogin);
		add(changePasswordGetToken);
		add(changePasswordByToken);
	}

	public static void setAsCurrent() {
		new Simple().run().setContent(new WelcomePage());
	}
}
