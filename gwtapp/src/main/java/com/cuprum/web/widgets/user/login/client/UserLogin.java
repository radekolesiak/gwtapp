package com.cuprum.web.widgets.user.login.client;

import com.cuprum.web.common.client.EndPoint;
import com.cuprum.web.common.client.ProcessFormPanel;
import com.cuprum.web.common.client.data.TUserSession;
import com.cuprum.web.common.client.exceptions.model.user.InvalidPasswordException;
import com.cuprum.web.common.client.exceptions.model.user.UserNotConfirmedException;
import com.cuprum.web.common.client.exceptions.model.user.UserNotFoundException;
import com.cuprum.web.widgets.common.client.StringValidator;
import com.cuprum.web.widgets.common.client.TextBox;
import com.cuprum.web.widgets.user.login.client.i18n.UserLoginMessages;
import com.cuprum.web.widgets.user.login.client.stub.IUserAuthentication;
import com.cuprum.web.widgets.user.login.client.stub.IUserAuthenticationAsync;
import com.google.gwt.core.client.GWT;

/**
 * Login widget.
 * 
 * @author Radek Olesiak
 * 
 */
public class UserLogin extends ProcessFormPanel<TUserSession> {
	private final UserLoginMessages messages = GWT
			.create(UserLoginMessages.class);

	private final TextBox login = new TextBox();

	private final TextBox password = new TextBox();

	private final IUserAuthenticationAsync endPoint = ((IUserAuthenticationAsync) EndPoint
			.create(GWT.create(IUserAuthentication.class)));

	@Override
	public String getSubmitMessage() {
		return messages.msgSubmit();
	}

	@Override
	public TUserSession getValue() {
		return value;
	}

	@Override
	protected void onAddFields() {
		setHeading(messages.msgHeading());

		login.setFieldLabel(messages.msgLoginLabel());
		login.setEmptyText(messages.msgLoginEmptyText());

		password.setFieldLabel(messages.msgPasswordLabel());
		password.setPassword(true);

		add(login);
		add(password);
	}

	@Override
	protected void onSubmit() {
		endPoint.login(login.getValue(), password.getValue(), getCallback());
	}

	@Override
	protected void onValidate(TUserSession value) {
		try {
			value.evalError();
			setValue(value);
		} catch (UserNotFoundException e) {
			login.setValidator(new StringValidator(messages.msgUserNotFound()));
		} catch (UserNotConfirmedException e) {
			login.setValidator(new StringValidator(messages
					.msgPleaseConfirmUser()));
		} catch (InvalidPasswordException e) {
			password.setValidator(new StringValidator(messages
					.msgInvalidPassword()));
		} catch (Throwable e) {
			login.setValidator(new StringValidator(e));
		}
	}

	private TUserSession value;

	@Override
	public void setValue(final TUserSession value) {
		this.value = value;
	}
}
