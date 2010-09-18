package org.gwtapp.extension.user.client;

import org.gwtapp.extension.user.client.api.ReCaptchaUserService;
import org.gwtapp.extension.user.client.api.ReCaptchaUserServiceAsync;
import org.gwtapp.extension.user.client.data.ReCaptchaUser;
import org.gwtapp.extension.user.client.data.exception.UserValidationException;
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

	private final UserPanel userPanel;

	@Inject
	public ReCaptchaUserRegisterServicePanel(Provider provider) {
		super(provider);
		this.userPanel = provider.userPanel;
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
				create(new ValidationAsyncCallback<Long, UserValidationException>() {
					@Override
					public void onSuccess(Long result) {
						user.setId(result);
						userPanel.getValidator().clearValidation();
						getValidator().clearValidation();
						Window.alert("SUCCESS: User has been added");
					}

					@Override
					public void onValidation(UserValidationException validation) {
						userPanel.getValidator().setValidation(validation);
						getValidator().setValidation(validation);
					}
				}));
	}
}
