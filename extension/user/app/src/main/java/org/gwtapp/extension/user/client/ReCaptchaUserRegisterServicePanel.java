package org.gwtapp.extension.user.client;

import org.gwtapp.core.client.SimpleAsyncCallback;
import org.gwtapp.extension.user.client.api.ReCaptchaUserService;
import org.gwtapp.extension.user.client.api.ReCaptchaUserServiceAsync;
import org.gwtapp.extension.user.client.data.ReCaptchaUser;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.inject.Inject;

public class ReCaptchaUserRegisterServicePanel extends
		ReCaptchaUserRegisterPanel {

	private static final ReCaptchaUserServiceAsync service = GWT
			.create(ReCaptchaUserService.class);

	@Inject
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
		// TODO move these exception samples as JUnit tests in gwtapp-core
		// module
		create(new SimpleAsyncCallback<Long>() {
			@Override
			public void onFailure(Throwable e) {
				GWT
						.log("ReCaptchaUserRegisterServicePanel::doUserRegister() AsyncCallback failure A");
				throw new IllegalStateException(e);
			}
		}).onFailure(new NullPointerException());
		create(new SimpleAsyncCallback<Long>() {
			@Override
			public void onFailure(Throwable e) {
				GWT
						.log("ReCaptchaUserRegisterServicePanel::doUserRegister() AsyncCallback failure B");
			}
		}).onFailure(new NullPointerException());
	}
}
