package org.gwtapp.extension.user.client;

import org.gwtapp.extension.user.client.api.ReCaptchaUserService;
import org.gwtapp.extension.user.client.api.ReCaptchaUserServiceAsync;
import org.gwtapp.extension.user.client.data.ReCaptchaUser;
import org.gwtapp.extension.user.client.data.exception.ReCaptchaUserValidationException;
import org.gwtapp.validation.client.ValidationAsyncCallback;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.inject.Inject;

public class ReCaptchaUserRegisterServicePanel extends
		ReCaptchaUserRegisterPanel {

	private static final ReCaptchaUserServiceAsync service = GWT
			.create(ReCaptchaUserService.class);

	@Inject
	public ReCaptchaUserRegisterServicePanel(Provider provider) {
		super(provider);
		addStateChangedHandler(new ValueChangeHandler<State>() {
			@Override
			public void onValueChange(ValueChangeEvent<State> event) {
				if (event.getValue() == State.REGISTER_BUTTON_CLICKED) {
					doUserRegister(getValue());
				}
			}
		});
	}

	private void doUserRegister(final ReCaptchaUser user) {
		setInProgress(true);
		service.addReCaptchaUser(
				user,
				create(new ValidationAsyncCallback<Long, ReCaptchaUserValidationException>() {
					@Override
					public void onCallbackSuccess(Long result) {
						onCallback();
						user.setId(result);
						getValidator().clearValidation();
						fireValueChangeEvent();
					}

					@Override
					public void onCallbackValidation(
							ReCaptchaUserValidationException validation) {
						onCallback();
						getValidator().setValidation(validation);
					}

					@Override
					public void onCallbackFailure(Throwable e) {
						onCallback();
					}

					private void onCallback() {
						setInProgress(false);
						ReCaptchaPanel.reload();
					}
				}));
	}
}
