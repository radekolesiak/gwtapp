package org.gwtapp.startapp.client;

import org.gwtapp.extension.user.client.ReCaptchaUserRegisterServicePanel;
import org.gwtapp.extension.user.client.data.ReCaptchaUser;
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

	public final static GinjectorService gin = GWT
			.create(GinjectorService.class);

	@Override
	public final void onModuleLoad() {
		RootPanel.get().add(new Label("GWT Manual Testing"));
		ReCaptchaUserRegisterServicePanel reCaptcha = gin
				.getReCaptchaUserRegisterServicePanel();
		assert reCaptcha != null;
		RootPanel.get().add(reCaptcha);
		reCaptcha.addChangeHandler(new ChangeHandler() {
			@Override
			public void onChange(ChangeEvent event) {
				RootPanel.get().add(
						new Label("ChangeEvent: reCaptcha has been loaded"));
			}
		});
		reCaptcha
				.addValueChangeHandler(new ValueChangeHandler<ReCaptchaUser>() {
					@Override
					public void onValueChange(
							ValueChangeEvent<ReCaptchaUser> value) {
						GWT.log(value.getValue().getClass().getName());
						GWT.log("userRegister-login: "
								+ value.getValue().getUser().getLogin());
						GWT.log("userRegister-email: "
								+ value.getValue().getUser().getEmail());
						GWT.log("userRegister-name: "
								+ value.getValue().getUser().getName());
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
