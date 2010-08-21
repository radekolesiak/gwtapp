package org.gwtapp.extension.user.client;

import org.gwtapp.core.client.AsyncCallbackInjector;
import org.gwtapp.core.client.SimpleAsyncCallback;
import org.gwtapp.extension.user.client.api.ReCaptchaUserService;
import org.gwtapp.extension.user.client.api.ReCaptchaUserServiceAsync;
import org.gwtapp.extension.user.client.data.ReCaptchaUserImpl;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.inject.Inject;

public class ReCaptchaUserRegisterServicePanel extends
		ReCaptchaUserRegisterPanel {

	private static final ReCaptchaUserServiceAsync service = GWT
			.create(ReCaptchaUserService.class);

	public static interface Injection {
		TemplateCallback getTemplateCallback();

		AsyncCallbackInjector getAsyncCallbackInjector();
	}

	private final AsyncCallbackInjector injector;

	@Inject
	public ReCaptchaUserRegisterServicePanel(Injection injection) {
		super(injection.getTemplateCallback());
		this.injector = injection.getAsyncCallbackInjector();
		addValueChangeHandler(new ValueChangeHandler<ReCaptchaUserImpl>() {
			@Override
			public void onValueChange(ValueChangeEvent<ReCaptchaUserImpl> event) {
				doUserRegister(event.getValue());
			}
		});
	}

	private void doUserRegister(ReCaptchaUserImpl user) {
		injector.create(new SimpleAsyncCallback<Long>()).onFailure(new NullPointerException());
	}
}
