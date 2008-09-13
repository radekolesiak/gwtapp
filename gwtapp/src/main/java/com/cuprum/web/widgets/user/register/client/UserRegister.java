package com.cuprum.web.widgets.user.register.client;

import com.cuprum.web.common.client.EndPoint;
import com.cuprum.web.common.client.ProcessFormPanel;
import com.cuprum.web.common.client.exceptions.RegExpException;
import com.cuprum.web.common.client.exceptions.model.user.MailAlreadyExistsException;
import com.cuprum.web.common.client.exceptions.model.user.UserAlreadyExistsException;
import com.cuprum.web.widgets.common.client.PasswordTextBoxes;
import com.cuprum.web.widgets.common.client.StringValidator;
import com.cuprum.web.widgets.common.client.TextBox;
import com.cuprum.web.widgets.common.client.TextBoxes;
import com.cuprum.web.widgets.common.client.exception.DualTextFieldInvalidException;
import com.cuprum.web.widgets.common.client.exception.TextToShortException;
import com.cuprum.web.widgets.user.password.client.data.TUserPassword;
import com.cuprum.web.widgets.user.register.client.data.TUserRegisterLoginValue;
import com.cuprum.web.widgets.user.register.client.data.TUserRegisterValue;
import com.cuprum.web.widgets.user.register.client.i18n.UserRegisterMessages;
import com.cuprum.web.widgets.user.register.client.stub.IUserRegister;
import com.cuprum.web.widgets.user.register.client.stub.IUserRegisterAsync;
import com.google.gwt.core.client.GWT;

public class UserRegister extends ProcessFormPanel<TUserRegisterValue> {
	public final static String CONFIRM_REQUEST = "confirm";

	private final IUserRegisterAsync endPoint = ((IUserRegisterAsync) EndPoint
			.create(GWT.create(IUserRegister.class)));

	private final UserRegisterMessages messages = GWT
			.create(UserRegisterMessages.class);

	private final TextBox login = new TextBox();

	private final PasswordTextBoxes password = new PasswordTextBoxes();

	private final TextBoxes mail = new TextBoxes();

	public TUserRegisterValue getValue() {
		TUserRegisterValue userRegister = new TUserRegisterValue();
		userRegister.login.set(login.getValue());
		userRegister.password.set(password.getValues());
		userRegister.mail.set(mail.getValues());
		return userRegister;
	}

	public void setValue(final TUserRegisterValue userRegister) {
		login.setValue(userRegister.login.get());
		password.setValue(userRegister.password.getDual());
		mail.setValue(userRegister.mail.getDual());
	}

	protected void onValidate(final TUserRegisterValue userRegister) {
		try {
			userRegister.getValueLogin().evalError();
		} catch (TextToShortException e) {
			login
					.setValidator(new StringValidator(
							messages
									.msgLoginTooShort(TUserRegisterLoginValue.MIN_LOGIN_LENGTH)));
		} catch (UserAlreadyExistsException e) {
			login.setValidator(new StringValidator(messages
					.msgLoginAlreadyExists()));
		} catch (RegExpException e) {
			login.setValidator(new StringValidator(messages.msgLoginFormat()));
		} catch (Throwable e) {
			login.setValidator(new StringValidator(e));
		}

		try {
			userRegister.getValuePassword().evalError();
		} catch (TextToShortException e) {
			password.setValidator(new StringValidator(messages
					.msgPasswordTooShort(TUserPassword.MIN_PASSWORD_LENGTH)));
		} catch (DualTextFieldInvalidException e) {
			password.setValidator(new StringValidator(messages
					.msgPasswordsDifferent()));
		} catch (Throwable e) {
			password.setValidator(new StringValidator(e));
		}

		try {
			userRegister.getValueMail().evalError();
		} catch (RegExpException e) {
			mail.setValidator(new StringValidator(e));
		} catch (DualTextFieldInvalidException e) {
			mail.setValidator(new StringValidator(e));
		} catch (MailAlreadyExistsException e) {
			mail.setValidator(new StringValidator(e));
		} catch (Throwable e) {
			mail.setValidator(new StringValidator(e));
		}
	}

	protected void onAddFields() {
		setHeading(messages.msgHeading());

		login.setFieldLabel(messages.msgLoginLabel());
		login.setEmptyText(messages.msgLoginEmptyText());

		password.setFieldLabel(messages.msgPasswordLabel(), messages
				.msgPasswordConfirmLabel());

		mail.setFieldLabel(messages.msgMailLabel(), messages
				.msgMailConfirmLabel());
		mail.setEmptyText(messages.msgMailEmptyText());

		add(login);
		password.attachTo(this);
		mail.attachTo(this);
	}

	protected void onSubmit() {
		endPoint.processUserRegister(getValue(), getCallback());
	}

	public String getSubmitMessage() {
		return messages.msgSubmit();
	}
}
