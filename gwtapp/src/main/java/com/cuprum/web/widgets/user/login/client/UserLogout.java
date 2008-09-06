package com.cuprum.web.widgets.user.login.client;

import com.cuprum.web.common.client.EndPoint;
import com.cuprum.web.common.client.SessionCallback;
import com.cuprum.web.common.client.data.TUserSession;
import com.cuprum.web.widgets.user.login.client.i18n.UserLogoutMessages;
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
public class UserLogout extends FormPanel {
	private final UserLogoutMessages messages = GWT
			.create(UserLogoutMessages.class);

	private final SessionCallback<Object> logoutCallback = new SessionCallback<Object>() {
		public void onBefore() {
			submit.setEnabled(true);
		}

		public void onSessionResponseSuccess(final Object object) {
			fireLogoutListener();
		}

		@Override
		public void onSessionNotFound() {
			fireLogoutSessionNotFoundListener();
		}
	};

	private final Button submit = new Button(messages.msgSubmit());

	/** Sets widgets. */
	public UserLogout() {

		setFrame(true);
		setWidth(400);
		setLabelWidth(125);
		setFieldWidth(210);
		setButtonAlign(HorizontalAlignment.CENTER);
		setHeading(messages.msgHeading());

		submit.addSelectionListener(new SelectionListener<ComponentEvent>() {
			public void componentSelected(ComponentEvent ce) {
				submit.setEnabled(false);
				((IUserAuthenticationAsync) EndPoint.create(GWT
						.create(IUserAuthentication.class))).logout(
						getSession(), logoutCallback);
			}
		});

		addButton(submit);
	}

	private TUserSession session = null;

	public void setSession(final TUserSession session) {
		this.session = session;
	}

	public TUserSession getSession() {
		return session;
	}

	LogoutListenerCollection logoutListeners = new LogoutListenerCollection();

	public void addLogoutListener(final LogoutListener listener) {
		logoutListeners.add(listener);
	}

	public void removeLogoutListener(final LogoutListener listener) {
		logoutListeners.remove(listener);
	}

	protected void fireLogoutSessionNotFoundListener() {
		logoutListeners.fireSessionNotFound(this);
	}

	protected void fireLogoutListener() {
		logoutListeners.fireLogoutListener(this);
	}
}
