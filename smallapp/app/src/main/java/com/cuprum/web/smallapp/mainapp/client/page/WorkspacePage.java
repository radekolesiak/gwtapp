package com.cuprum.web.smallapp.mainapp.client.page;

import java.util.Date;

import com.cuprum.web.common.client.SessionNotFoundListener;
import com.cuprum.web.common.client.UserEndPoint;
import com.cuprum.web.common.client.data.TUserSession;
import com.cuprum.web.smallapp.mainapp.client.MainApp;
import com.cuprum.web.smallapp.mainapp.client.SmallAppCallback;
import com.cuprum.web.smallapp.mainapp.client.stub.ISmallApp;
import com.cuprum.web.smallapp.mainapp.client.stub.ISmallAppAsync;
import com.cuprum.web.widgets.common.client.SubmitListener;
import com.cuprum.web.widgets.user.login.client.UserLogout;
import com.cuprum.web.widgets.user.password.client.ChangePasswordByUser;
import com.extjs.gxt.ui.client.widget.form.LabelField;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class WorkspacePage extends VerticalPanel {
	final ChangePasswordByUser changePasswordByUser = new ChangePasswordByUser();

	public WorkspacePage() {
		final UserLogout userLogout = new UserLogout();
		userLogout.setSession(TUserSession.getSession());
		userLogout.addSubmitListener(new SubmitListener() {
			public void onSubmit(Widget widget) {
				MainApp.showWelcome();
			}
		});

		userLogout.addSessionNotFoundListener(new SessionNotFoundListener() {
			public void onSessionNotFound(Widget widget) {
				MainApp.showWelcome();
			}
		});

		add(userLogout);

		Button activity = new Button("Last Activity");
		activity.addClickListener(new ClickListener() {
			public void onClick(final Widget sender) {
				SmallAppCallback<Date> callback = new SmallAppCallback<Date>() {
					public void onSessionResponseSuccess(final Date date) {
						add(new LabelField(date + ""));
					}
				};
				((ISmallAppAsync) UserEndPoint.create(GWT
						.create(ISmallApp.class)))
						.getLastSessionActivity(callback);
			}
		});

		Button remove = new Button("Force remove session");
		remove.addClickListener(new ClickListener() {
			public void onClick(final Widget sender) {
				SmallAppCallback<Object> callback = new SmallAppCallback<Object>() {
					public void onSessionResponseSuccess(final Object arg) {
						add(new LabelField("Session has been removed"));
					}
				};
				((ISmallAppAsync) UserEndPoint.create(GWT
						.create(ISmallApp.class))).forceRemoveSession(callback);
			}
		});

		changePasswordByUser.addSubmitListener(new SubmitListener() {
			public void onSubmit(Widget sender) {
				add(new LabelField("Password has been changed"));
			}
		});

		changePasswordByUser.addSessionNotFoundListener(MainApp
				.getDefaultSessionNotFoundListener());

		add(activity);
		add(remove);
		add(changePasswordByUser);
	}
}
