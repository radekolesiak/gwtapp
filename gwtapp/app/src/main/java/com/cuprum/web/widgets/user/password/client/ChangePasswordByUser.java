package com.cuprum.web.widgets.user.password.client;

import com.cuprum.web.common.client.UserEndPoint;
import com.cuprum.web.common.client.exceptions.DualTextFieldInvalidException;
import com.cuprum.web.common.client.exceptions.TextToShortException;
import com.cuprum.web.common.client.exceptions.model.user.InvalidPasswordException;
import com.cuprum.web.widgets.common.client.PasswordTextBoxes;
import com.cuprum.web.widgets.common.client.ProcessFormPanel;
import com.cuprum.web.widgets.common.client.StringValidator;
import com.cuprum.web.widgets.common.client.TextBox;
import com.cuprum.web.widgets.user.password.client.data.TChangePasswordByUser;
import com.cuprum.web.widgets.user.password.client.data.TUserPassword;
import com.cuprum.web.widgets.user.password.client.i18n.ChangePasswordByUserMessages;
import com.cuprum.web.widgets.user.password.client.stub.IUserPassword;
import com.cuprum.web.widgets.user.password.client.stub.IUserPasswordAsync;
import com.google.gwt.core.client.GWT;

public class ChangePasswordByUser extends
		ProcessFormPanel<TChangePasswordByUser> {
	private final IUserPasswordAsync endPoint = ((IUserPasswordAsync) UserEndPoint
			.create(GWT.create(IUserPassword.class)));

	private ChangePasswordByUserMessages messages = GWT
			.create(ChangePasswordByUserMessages.class);

	private final TextBox oldPassword = new TextBox();

	private final PasswordTextBoxes newPassword = new PasswordTextBoxes();

	@Override
	protected void onAddFields() {
		setHeading(messages.msgHeading());

		oldPassword.setFieldLabel(messages.msgOldPasswordLabel());
		oldPassword.setPassword(true);

		newPassword.setFieldLabel(messages.msgNewPasswordLabel(), messages
				.msgNewPasswordConfirmLabel());

		add(oldPassword);
		newPassword.attachTo(this);
	}

	@Override
	protected void onValidate(TChangePasswordByUser passwords) {
		try {
			passwords.oldPassword.evalError();
		} catch (InvalidPasswordException e) {
			oldPassword.setValidator(new StringValidator(messages
					.msgInvalidOldPasswordLabel()));
		} catch (Throwable e) {
			oldPassword.setValidator(new StringValidator(e));
		}

		try {
			passwords.newPassword.evalError();
		} catch (DualTextFieldInvalidException e) {
			newPassword.setValidator(new StringValidator(messages
					.msgNewPasswordsDifferent()));
		} catch (TextToShortException e) {
			newPassword
					.setValidator(new StringValidator(
							messages
									.msgNewPasswordTooShort(TUserPassword.MIN_PASSWORD_LENGTH)));
		} catch (Throwable e) {
			newPassword.setValidator(new StringValidator(e));
		}
	}

	@Override
	public TChangePasswordByUser getValue() {
		TChangePasswordByUser passwords = new TChangePasswordByUser();
		passwords.oldPassword.set(oldPassword.getValue());
		passwords.newPassword.set(newPassword.getValues());
		return passwords;
	}

	@Override
	public void setValue(final TChangePasswordByUser passwords) {
		oldPassword.setValue(passwords.oldPassword.get());
		newPassword.setValue(passwords.newPassword.getDual());
	}

	@Override
	protected void onSubmit() {
		endPoint.processChangePasswordByUser(getValue(), getCallback());
	}

	@Override
	public String getSubmitMessage() {
		return messages.msgSubmit();
	}
}
