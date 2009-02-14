package com.cuprum.web.widgets.user.login.client;

import com.cuprum.web.common.client.EndPoint;
import com.cuprum.web.common.client.data.TUserSession;
import com.cuprum.web.widgets.common.client.ProcessFormPanel;
import com.cuprum.web.widgets.user.login.client.i18n.UserLogoutMessages;
import com.cuprum.web.widgets.user.login.client.stub.IUserLogin;
import com.cuprum.web.widgets.user.login.client.stub.IUserLoginAsync;
import com.google.gwt.core.client.GWT;

/**
 * Login widget.
 * 
 * @author Radek Olesiak
 * 
 */
public class UserLogout extends ProcessFormPanel<Object> {
	private final IUserLoginAsync endPoint = (IUserLoginAsync) EndPoint
			.create(GWT.create(IUserLogin.class));

	private final UserLogoutMessages messages = GWT
			.create(UserLogoutMessages.class);

	private TUserSession session = null;

	public void setSession(final TUserSession session) {
		this.session = session;
	}

	public TUserSession getSession() {
		return session;
	}

	@Override
	public String getSubmitMessage() {
		return messages.msgSubmit();
	}

	@Override
	public Object getValue() {
		return new Object();
	}

	@Override
	protected void onAddFields() {
		setHeading(messages.msgHeading());
	}

	@Override
	protected void onSubmit() {
		endPoint.logout(getSession(), getCallback());
	}

	@Override
	protected void onValidate(Object value) {
	}

	@Override
	public void setValue(Object value) {
	}
}
