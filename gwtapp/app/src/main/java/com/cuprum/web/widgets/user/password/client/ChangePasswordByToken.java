package com.cuprum.web.widgets.user.password.client;

import com.cuprum.web.common.client.UserEndPoint;
import com.cuprum.web.common.client.exceptions.DualTextFieldInvalidException;
import com.cuprum.web.common.client.exceptions.TextToShortException;
import com.cuprum.web.widgets.common.client.PasswordTextBoxes;
import com.cuprum.web.widgets.common.client.ProcessFormPanel;
import com.cuprum.web.widgets.common.client.StringValidator;
import com.cuprum.web.widgets.user.password.client.data.TChangePasswordByToken;
import com.cuprum.web.widgets.user.password.client.data.TUserPassword;
import com.cuprum.web.widgets.user.password.client.i18n.ChangePasswordByTokenMessages;
import com.cuprum.web.widgets.user.password.client.stub.IUserPassword;
import com.cuprum.web.widgets.user.password.client.stub.IUserPasswordAsync;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;

public class ChangePasswordByToken extends
		ProcessFormPanel<TChangePasswordByToken> {

	private final IUserPasswordAsync endPoint = ((IUserPasswordAsync) UserEndPoint
			.create(GWT.create(IUserPassword.class)));

	private final ChangePasswordByTokenMessages messages = GWT
			.create(ChangePasswordByTokenMessages.class);

	private final PasswordTextBoxes password = new PasswordTextBoxes();

	private String uid = Window.Location.getParameter(IUserPassword.REMIND_REQUEST);

	@Override
	public String getSubmitMessage() {
		return messages.msgSubmit();
	}

	@Override
	public TChangePasswordByToken getValue() {
		TChangePasswordByToken value = new TChangePasswordByToken();
		value.uid.set(uid);
		value.password.set(password.getValues());
		return value;
	}

	@Override
	protected void onAddFields() {
		setHeading(messages.msgHeading());
		password.setFieldLabel(messages.msgPasswordLabel(), messages
				.msgPasswordConfirmLabel());
		password.attachTo(this);
	}

	@Override
	protected void onSubmit() {
		endPoint.changePasswordByToken(getValue(), getCallback());
	}

	@Override
	protected void onValidate(TChangePasswordByToken value) {
		try {
			value.password.evalError();
		} catch (DualTextFieldInvalidException e) {
			password.setValidator(new StringValidator(messages
					.msgPasswordsDifferent()));
		} catch (TextToShortException e) {
			password.setValidator(new StringValidator(messages
					.msgPasswordTooShort(TUserPassword.MIN_PASSWORD_LENGTH)));
		} catch (Throwable e) {
			password.setValidator(new StringValidator(e));
		}
	}

	@Override
	public void setValue(TChangePasswordByToken value) {
		password.setValue(value.password.get());
		uid = value.uid.get();
	}
}
