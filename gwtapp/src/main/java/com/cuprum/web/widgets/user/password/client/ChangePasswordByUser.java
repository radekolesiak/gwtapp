package com.cuprum.web.widgets.user.password.client;

import com.cuprum.web.common.client.SessionCallback;
import com.cuprum.web.common.client.UserEndPoint;
import com.cuprum.web.common.client.Validate;
import com.cuprum.web.smallapp.mainapp.client.i18n.InfoMessages;
import com.cuprum.web.widgets.common.client.PasswordTextBoxes;
import com.cuprum.web.widgets.common.client.StringValidator;
import com.cuprum.web.widgets.common.client.TextBox;
import com.cuprum.web.widgets.user.password.client.data.TChangePasswordByUser;
import com.cuprum.web.widgets.user.password.client.i18n.ChangePasswordByUserMessages;
import com.cuprum.web.widgets.user.password.client.stub.IUserPassword;
import com.cuprum.web.widgets.user.password.client.stub.IUserPasswordAsync;
import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.event.ComponentEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.widget.Info;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.FormPanel;
import com.google.gwt.core.client.GWT;

public class ChangePasswordByUser extends FormPanel {
	private final IUserPasswordAsync endPoint = ((IUserPasswordAsync) UserEndPoint
			.create(GWT.create(IUserPassword.class)));

	ChangePasswordByUserMessages messages = GWT
			.create(ChangePasswordByUserMessages.class);

	private final TextBox oldPassword = new TextBox();

	private final PasswordTextBoxes newPassword = new PasswordTextBoxes();

	private final Button submit = new Button(messages.msgSubmit());

	private final SessionCallback<TChangePasswordByUser> callback = new SessionCallback<TChangePasswordByUser>() {
		@Override
		public void onBefore() {
			submit.setEnabled(true);
		}

		@Override
		public void onSessionResponseSuccess(
				final TChangePasswordByUser passwords) {
			validate(passwords);
		}

		@Override
		public void onSessionNotFound() {
			InfoMessages messages = GWT.create(InfoMessages.class);
			Info.display(messages.msgSessionNotFoundTitle(), messages
					.msgSessionNotFound(), new String[] {});
		}
	};

	public void validate(TChangePasswordByUser passwords) {
		Validate.init(ChangePasswordByUser.this);

		try {
			passwords.oldPassword.evalError();
		} catch (Throwable e) {
			oldPassword.setValidator(new StringValidator(e));
		}

		try {
			passwords.newPassword.evalError();
		} catch (Throwable e) {
			newPassword.setValidator(new StringValidator(e));
		}

		Validate.done(ChangePasswordByUser.this);
	}

	public TChangePasswordByUser getChangePasswordByUser() {
		TChangePasswordByUser passwords = new TChangePasswordByUser();
		passwords.oldPassword.set(oldPassword.getValue());
		passwords.newPassword.set(newPassword.getValues());
		return passwords;
	}

	public void setUserRegister(final TChangePasswordByUser passwords) {
		oldPassword.setValue(passwords.oldPassword.get());
		newPassword.setValue(passwords.newPassword.getDual());
	}

	public ChangePasswordByUser() {
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

		oldPassword.setFieldLabel(messages.msgOldPasswordLabel());
		oldPassword.setPassword(true);

		newPassword.setFieldLabel(messages.msgNewPasswordLabel(), messages
				.msgNewPasswordConfirmLabel());

		add(oldPassword);
		newPassword.attachTo(this);
		addButton(submit);
	}

	public void submit() {
		submit.setEnabled(false);
		endPoint.processChangePasswordByUser(getChangePasswordByUser(),
				callback);
	}
}
