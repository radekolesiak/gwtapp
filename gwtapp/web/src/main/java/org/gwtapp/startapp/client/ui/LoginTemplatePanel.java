package org.gwtapp.startapp.client.ui;

import org.gwtapp.startapp.client.StartApp;
import org.gwtapp.startapp.rpc.data.user.register.UserRegister;
import org.gwtapp.template.client.WidgetHandler;
import org.gwtapp.template.client.ui.TemplateFormPanel;

import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

public class LoginTemplatePanel extends TemplateFormPanel<String> {
	public LoginTemplatePanel() {
		super(StartApp.syncTemplateService.load("login.jsp"));
		addWidgetHandler("login", new WidgetHandler() {
			@Override
			public Widget onWidget(final String id) {
				// wrap text box
				class Login extends TextBox {
					public Login() {
						super(DOM.getElementById(id));
					}
				}
				Login login = new Login();
				addField(UserRegister.LOGIN.name(), login);
				return login;
			}
		});
	}
}
