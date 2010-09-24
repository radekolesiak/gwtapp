package org.gwtapp.extension.user.client;

import org.gwtapp.extension.user.client.api.ReCaptchaUserService;
import org.gwtapp.extension.user.client.api.ReCaptchaUserServiceAsync;
import org.gwtapp.extension.user.client.data.ReCaptchaUser;
import org.gwtapp.extension.user.client.data.exception.ReCaptchaUserValidationException;
import org.gwtapp.validation.client.ValidationAsyncCallback;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.Window;
import com.google.inject.Inject;

public class ReCaptchaUserRegisterServicePanel extends
		ReCaptchaUserRegisterPanel {

	private static final ReCaptchaUserServiceAsync service = GWT
			.create(ReCaptchaUserService.class);

	@Inject
	public ReCaptchaUserRegisterServicePanel(Provider provider) {
		super(provider);
		addValueChangeHandler(new ValueChangeHandler<ReCaptchaUser>() {
			@Override
			public void onValueChange(ValueChangeEvent<ReCaptchaUser> event) {
				doUserRegister(event.getValue());
			}
		});
	}

	private void doUserRegister(final ReCaptchaUser user) {
		service.addReCaptchaUser(
				user,
				create(new ValidationAsyncCallback<Long, ReCaptchaUserValidationException>() {
					@Override
					public void onCallbackSuccess(Long result) {
						ReCaptchaPanel.reload();
						user.setId(result);
						getValidator().clearValidation();
						Window.alert("SUCCESS: User has been added");
					}

					@Override
					public void onCallbackValidation(
							ReCaptchaUserValidationException validation) {
						if (validation
								.get(ReCaptchaUserValidationException.RECAPTCHA) == null) {
							ReCaptchaPanel.reload();
						}
						getValidator().setValidation(validation);
					}

					@Override
					public void onCallbackFailure(Throwable e) {
						ReCaptchaPanel.reload();
					}
				}));
	}
}
