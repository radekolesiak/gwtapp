package com.cuprum.web.smallapp.mainapp.client.page;

import java.util.Date;

import com.cuprum.web.common.client.data.TConnectionSession;
import com.cuprum.web.common.client.data.TUserSession;
import com.cuprum.web.smallapp.mainapp.client.MainApp;
import com.cuprum.web.smallapp.mainapp.client.i18n.MainAppMessages;
import com.cuprum.web.widgets.common.client.SubmitListener;
import com.cuprum.web.widgets.user.login.client.UserLogin;
import com.cuprum.web.widgets.user.password.client.ChangePasswordGetToken;
import com.cuprum.web.widgets.user.password.client.data.TChangePasswordGetToken;
import com.cuprum.web.widgets.user.register.client.UserRegister;
import com.cuprum.web.widgets.user.register.client.data.TUserRegisterValue;
import com.extjs.gxt.ui.client.widget.form.LabelField;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class WelcomePage extends VerticalPanel {
	final UserRegister userRegister = new UserRegister();

	final UserLogin userLogin = new UserLogin();

	final ChangePasswordGetToken changePasswordGetToken = new ChangePasswordGetToken();

	public WelcomePage() {
		MainAppMessages messages = GWT.create(MainAppMessages.class);

		Window.setTitle("SmallApp :: " + TConnectionSession.getSession().get()
				+ " :: " + messages.msgHelloWorld());

		userLogin.addSubmitListener(new SubmitListener() {
			public void onSubmit(Widget sender) {
				TUserSession.setSession(userLogin.getValue());
				if (userLogin.getValue() != null
						&& userLogin.getValue().get() != null) {
					MainApp.showWorkspace();
				}
			}
		});

		userRegister.addSubmitListener(new SubmitListener() {
			public void onSubmit(Widget sender) {
				userRegister.setValue(new TUserRegisterValue());
				add(new LabelField(
						"Account has been created. Receive email and click on link to confirm registration."));

			}
		});

		changePasswordGetToken.addSubmitListener(new SubmitListener() {
			public void onSubmit(Widget sender) {
				changePasswordGetToken.setValue(new TChangePasswordGetToken());
				add(new LabelField(
						"Token to change password has been sent. Receive email and click on link to change password."));
			}
		});

		Button activity = new Button("Activity");
		activity.addClickListener(new ClickListener() {
			public void onClick(final Widget sender) {
				add(new LabelField(new Date() + ""));
			}
		});

		add(new HTML("<small>This proxyapp-app is only client side code<br />  and shows how to write a client side application<br />  without access to the smallapp-srv server side source code.<br /> The proxyapp-app uses only shared common<br />  gwtapp (rpc & client) and smallapp-rpc source code.</small>"));
		add(userRegister);
		add(userLogin);
		add(changePasswordGetToken);
		add(activity);
	}
}
