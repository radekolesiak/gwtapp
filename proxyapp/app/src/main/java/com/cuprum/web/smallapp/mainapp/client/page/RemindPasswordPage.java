package com.cuprum.web.smallapp.mainapp.client.page;

import com.cuprum.web.smallapp.mainapp.client.MainApp;
import com.cuprum.web.widgets.common.client.SubmitListener;
import com.cuprum.web.widgets.user.password.client.ChangePasswordByToken;
import com.extjs.gxt.ui.client.widget.form.LabelField;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class RemindPasswordPage extends VerticalPanel {
	ChangePasswordByToken change = new ChangePasswordByToken();

	public RemindPasswordPage() {
		add(new Label("Change your password here:"));
		add(change);
		change.addSubmitListener(new SubmitListener() {
			public void onSubmit(Widget sender) {
				Button toLogin = new Button("Go to login");
				toLogin.addClickListener(new ClickListener() {
					public void onClick(Widget arg0) {
						MainApp.showWelcome();
					}
				});
				RemindPasswordPage.this.remove(change);
				RemindPasswordPage.this.add(new LabelField(
						"Your password has been changed"));
				RemindPasswordPage.this.add(toLogin);
			}
		});
	}
}
