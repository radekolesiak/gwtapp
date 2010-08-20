package org.gwtapp.extension.user.client;

import org.gwtapp.extension.user.client.api.ReCaptchaUserService;
import org.gwtapp.extension.user.client.api.ReCaptchaUserServiceAsync;
import org.gwtapp.extension.user.client.data.ReCaptchaUser;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;

public class ReCaptchaUserRegisterServicePanel extends
		ReCaptchaUserRegisterPanel {

	private static final ReCaptchaUserServiceAsync service = GWT
			.create(ReCaptchaUserService.class);

	public ReCaptchaUserRegisterServicePanel(TemplateCallback callback) {
		super(callback);
		addValueChangeHandler(new ValueChangeHandler<ReCaptchaUser>() {
			@Override
			public void onValueChange(ValueChangeEvent<ReCaptchaUser> event) {
				doUserRegister(event.getValue());
			}
		});
	}

	private void doUserRegister(ReCaptchaUser user) {
	}
}
