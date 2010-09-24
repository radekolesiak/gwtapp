package org.gwtapp.startapp.client.test;

import org.gwtapp.extension.user.client.ReCaptchaUserRegisterServicePanel;
import org.gwtapp.extension.user.client.data.ReCaptchaUser;
import org.gwtapp.startapp.client.StartAppManualTestEntryPoint;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;

public class ReCaptchaPanelTestManual extends FlowPanel {
	public ReCaptchaPanelTestManual() {
		ReCaptchaUserRegisterServicePanel reCaptcha = StartAppManualTestEntryPoint.gin
				.getReCaptchaUserRegisterServicePanel();
		assert reCaptcha != null;
		add(reCaptcha);
		reCaptcha.addChangeHandler(new ChangeHandler() {
			@Override
			public void onChange(ChangeEvent event) {
				add(new Label("ChangeEvent: reCaptcha has been loaded"));
			}
		});
		reCaptcha
				.addValueChangeHandler(new ValueChangeHandler<ReCaptchaUser>() {
					@Override
					public void onValueChange(
							ValueChangeEvent<ReCaptchaUser> value) {
						Window.alert("SUCCESS: '"
								+ value.getValue().getUser().getLogin()
								+ "' user has been added");
						GWT.log(value.getValue().getClass().getName());
						GWT.log("userRegister-login: "
								+ value.getValue().getUser().getLogin());
						GWT.log("userRegister-email: "
								+ value.getValue().getUser().getEmail());
						GWT.log("userRegister-password: "
								+ value.getValue().getPassword());
						GWT.log("userRegister-password-verify: "
								+ value.getValue().getPasswordVerify());
						GWT.log("userRegister-response: "
								+ value.getValue().getResponse());
						GWT.log("userRegister-challenge: "
								+ value.getValue().getChallenge());
					}
				});
	}
}
