package org.gwtapp.startapp.client;

import org.gwtapp.extension.user.client.ReCaptchaUserRegisterServicePanel;
import org.gwtapp.extension.user.client.data.ReCaptchaUserImpl;
import org.gwtapp.template.client.TemplateRepository;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;

public class StartAppManualTestEntryPoint implements EntryPoint {

	public final static TemplateRepository template = new TemplateRepository(
			"/templates/");

	@Override
	public final void onModuleLoad() {
		GIN gin = GWT.create(GIN.class);
		RootPanel.get().add(new Label("GWT Manual Testing"));
		ReCaptchaUserRegisterServicePanel reCaptcha = gin
				.getReCaptchaUserRegisterServicePanel();
		RootPanel.get().add(reCaptcha);
		reCaptcha.addChangeHandler(new ChangeHandler() {
			@Override
			public void onChange(ChangeEvent event) {
				RootPanel.get().add(
						new Label("ChangeEvent: reCaptcha has been loaded"));
			}
		});
		reCaptcha
				.addValueChangeHandler(new ValueChangeHandler<ReCaptchaUserImpl>() {
					@Override
					public void onValueChange(
							ValueChangeEvent<ReCaptchaUserImpl> value) {
						GWT.log("userRegister-login: "
								+ value.getValue().getLogin());
						GWT.log("userRegister-email: "
								+ value.getValue().getEmail());
						GWT.log("userRegister-name: "
								+ value.getValue().getName());
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
