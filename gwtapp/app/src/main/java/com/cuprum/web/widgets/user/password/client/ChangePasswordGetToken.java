package com.cuprum.web.widgets.user.password.client;

import com.cuprum.web.common.client.UserEndPoint;
import com.cuprum.web.common.client.exceptions.model.user.UserNotConfirmedException;
import com.cuprum.web.common.client.exceptions.model.user.UserNotFoundException;
import com.cuprum.web.widgets.common.client.ProcessFormPanel;
import com.cuprum.web.widgets.common.client.StringValidator;
import com.cuprum.web.widgets.common.client.TextBox;
import com.cuprum.web.widgets.user.password.client.data.TChangePasswordGetToken;
import com.cuprum.web.widgets.user.password.client.i18n.ChangePasswordGetTokenMessages;
import com.cuprum.web.widgets.user.password.client.stub.IUserPassword;
import com.cuprum.web.widgets.user.password.client.stub.IUserPasswordAsync;
import com.google.gwt.core.client.GWT;

public class ChangePasswordGetToken extends
		ProcessFormPanel<TChangePasswordGetToken> {

	private final IUserPasswordAsync endPoint = ((IUserPasswordAsync) UserEndPoint
			.create(GWT.create(IUserPassword.class)));

	private final ChangePasswordGetTokenMessages messages = GWT
			.create(ChangePasswordGetTokenMessages.class);

	private final TextBox login = new TextBox();

	@Override
	public String getSubmitMessage() {
		return messages.msgSubmit();
	}

	@Override
	public TChangePasswordGetToken getValue() {
		TChangePasswordGetToken value = new TChangePasswordGetToken();
		value.set(login.getValue());
		return value;
	}

	@Override
	protected void onAddFields() {
		setHeading(messages.msgHeading());

		login.setFieldLabel(messages.msgLoginLabel());
		login.setEmptyText(messages.msgLoginEmptyText());

		add(login);
	}

	@Override
	protected void onSubmit() {
		endPoint.changePasswordGetToken(getValue(), getCallback());
	}

	@Override
	protected void onValidate(TChangePasswordGetToken value) {
		try {
			value.evalError();
		} catch (UserNotFoundException e) {
			login
					.setValidator(new StringValidator(messages
							.msgLoginNotFound()));
		} catch (UserNotConfirmedException e) {
			login.setValidator(new StringValidator(messages
					.msgLoginNotConfirmed()));
		} catch (Throwable e) {
			login.setValidator(new StringValidator(e));
		}
	}

	@Override
	public void setValue(TChangePasswordGetToken value) {
		login.setValue(value.get());
	}
}
