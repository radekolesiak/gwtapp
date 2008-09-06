package com.cuprum.web.widgets.user.login.client;

import com.cuprum.web.common.client.EndPoint;
import com.cuprum.web.common.client.Validate;
import com.cuprum.web.common.client.WebCallback;
import com.cuprum.web.common.client.data.TUserSession;
import com.cuprum.web.common.client.exceptions.model.user.InvalidPasswordException;
import com.cuprum.web.common.client.exceptions.model.user.UserNotFoundException;
import com.cuprum.web.widgets.common.client.StringValidator;
import com.cuprum.web.widgets.common.client.TextBox;
import com.cuprum.web.widgets.user.login.client.i18n.UserLoginMessages;
import com.cuprum.web.widgets.user.login.client.stub.IUserAuthentication;
import com.cuprum.web.widgets.user.login.client.stub.IUserAuthenticationAsync;
import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.event.ComponentEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.FormPanel;
import com.google.gwt.core.client.GWT;

/**
 * Login widget.
 * 
 * @author Radek Olesiak
 * 
 */
public class UserLogin extends FormPanel {
	private final UserLoginMessages messages = GWT
			.create(UserLoginMessages.class);

	private final TextBox login = new TextBox();

	private final TextBox password = new TextBox();

	private final Button submit = new Button(messages.msgSubmit());

	private final WebCallback<TUserSession> loginCallback = new WebCallback<TUserSession>() {
		@Override
		public void onBefore() {
			submit.setEnabled(true);
		}

		@Override
		public void onResponseSuccess(final TUserSession session) {
			Validate.init(UserLogin.this);
			try {
				session.evalError();
				fireLoginListener(session);
			} catch (UserNotFoundException e) {
				login.setValidator(new StringValidator(messages
						.msgUserNotFound()));
			} catch (InvalidPasswordException e) {
				password.setValidator(new StringValidator(messages
						.msgInvalidPassword()));
			} catch (Throwable e) {
			}
			Validate.done(UserLogin.this);
		}
	};

	/** Sets widgets. */
	public UserLogin() {
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

		password.setFieldLabel(messages.msgPasswordLabel());
		password.setPassword(true);

		add(login);
		add(password);
		addButton(submit);
	}

	public void submit() {
		submit.setEnabled(false);
		endPoint.login(login.getValue(), password.getValue(), loginCallback);
	}

	private final IUserAuthenticationAsync endPoint = ((IUserAuthenticationAsync) EndPoint
			.create(GWT.create(IUserAuthentication.class)));

	public final LoginListenerCollection loginListeners = new LoginListenerCollection();

	public void addLoginListener(LoginListener listener) {
		loginListeners.add(listener);
	}

	public void removeLoginListener(LoginListener listener) {
		loginListeners.remove(listener);
	}

	protected void fireLoginListener(TUserSession session) {
		loginListeners.fireLoginListener(this, session);
	}
}
