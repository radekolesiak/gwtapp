package com.cuprum.web.widgets.user.register.client;

import com.cuprum.web.common.client.EndPoint;
import com.cuprum.web.common.client.Validate;
import com.cuprum.web.common.client.WebCallback;
import com.cuprum.web.common.client.exceptions.RegExpException;
import com.cuprum.web.common.client.exceptions.model.user.MailAlreadyExistsException;
import com.cuprum.web.common.client.exceptions.model.user.UserAlreadyExistsException;
import com.cuprum.web.widgets.common.client.PasswordTextBoxes;
import com.cuprum.web.widgets.common.client.StringValidator;
import com.cuprum.web.widgets.common.client.SubmitListener;
import com.cuprum.web.widgets.common.client.SubmitListenerCollection;
import com.cuprum.web.widgets.common.client.TextBox;
import com.cuprum.web.widgets.common.client.TextBoxes;
import com.cuprum.web.widgets.common.client.exception.DualTextFieldInvalidException;
import com.cuprum.web.widgets.common.client.exception.TextToShortException;
import com.cuprum.web.widgets.user.register.client.data.TUserRegisterValue;
import com.cuprum.web.widgets.user.register.client.i18n.UserRegisterMessages;
import com.cuprum.web.widgets.user.register.client.stub.IUserRegister;
import com.cuprum.web.widgets.user.register.client.stub.IUserRegisterAsync;
import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.event.ComponentEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.FormPanel;
import com.google.gwt.core.client.GWT;

public class UserRegister extends FormPanel {
	private final UserRegisterMessages messages = GWT
			.create(UserRegisterMessages.class);

	private final TextBox login = new TextBox();

	private final PasswordTextBoxes password = new PasswordTextBoxes();

	private final TextBoxes mail = new TextBoxes();

	private final Button submit = new Button(messages.msgSubmit());

	TUserRegisterValue userRegister = new TUserRegisterValue();

	// TODO: onFailure
	private final WebCallback<TUserRegisterValue> verifyCallback = new WebCallback<TUserRegisterValue>() {
		public void onBefore() {
			submit.setEnabled(true);
		}

		public void onResponseFailure(Throwable e) {
			super.onResponseFailure(e);
			fireSubmitListener(false);
		}

		// result process (multiple exceptions)
		public void onResponseSuccess(TUserRegisterValue rpcUserRegister) {
			userRegister = rpcUserRegister;

			Validate.init(UserRegister.this);

			try {
				userRegister.getValueLogin().evalError();
			} catch (TextToShortException e) {
				login
						.setValidator(new StringValidator(
								messages
										.msgLoginTooShort(+TUserRegisterValue.MIN_LOGIN_LENGTH)));
			} catch (UserAlreadyExistsException e) {
				login.setValidator(new StringValidator(messages
						.msgLoginAlreadyExists()));
			} catch (RegExpException e) {
				login.setValidator(new StringValidator(messages
						.msgLoginFormat()));
			} catch (Throwable e) {
				login.setValidator(new StringValidator(e));
			}

			try {
				userRegister.getValuePassword().evalError();
			} catch (TextToShortException e) {
				password
						.setValidator(new StringValidator(
								messages
										.msgPasswordTooShort(TUserRegisterValue.MIN_PASSWORD_LENGTH)));
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

			Validate.done(UserRegister.this);

			fireSubmitListener(!userRegister.hasErrors());
		}
	};

	private final IUserRegisterAsync endPoint = ((IUserRegisterAsync) EndPoint
			.create(GWT.create(IUserRegister.class)));

	// form definition
	public UserRegister() {
		submit.addSelectionListener(new SelectionListener<ComponentEvent>() {
			public void componentSelected(ComponentEvent ce) {
				submit();
			}
		});

		setFrame(true);
		setWidth(400);
		setLabelWidth(125);
		setFieldWidth(210);
		setButtonAlign(HorizontalAlignment.CENTER);
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
		addButton(submit);
	}

	// data process definition
	public void submit() {
		userRegister.login.set(login.getValue());
		userRegister.password.set(password.getValues());
		userRegister.mail.set(mail.getValues());

		submit.setEnabled(false);
		endPoint.processUserRegister(userRegister, verifyCallback);
	}

	private final SubmitListenerCollection submitListeners = new SubmitListenerCollection();

	public void addSubmitListener(SubmitListener listener) {
		submitListeners.add(listener);
	}

	public void removeSubmitListener(SubmitListener listener) {
		submitListeners.remove(listener);
	}

	protected void fireSubmitListener(boolean doSubmit) {
		submitListeners.fireSubmitListener(this, doSubmit);
	}
}
