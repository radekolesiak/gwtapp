package com.cuprum.web.smallapp.mainapp.client;

import java.util.Date;

import com.cuprum.web.common.client.UserEndPoint;
import com.cuprum.web.common.client.data.TUserSession;
import com.cuprum.web.smallapp.mainapp.client.stub.ISmallApp;
import com.cuprum.web.smallapp.mainapp.client.stub.ISmallAppAsync;
import com.cuprum.web.templates.extsimple.client.ExtSimple;
import com.cuprum.web.widgets.user.login.client.LogoutListener;
import com.cuprum.web.widgets.user.login.client.UserLogout;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class Workspace extends VerticalPanel {
	public Workspace() {
		RootPanel.get().addStyleName("smallapp");

		final UserLogout userLogout = new UserLogout();
		userLogout.setSession(TUserSession.getSession());
		userLogout.addLogoutListener(new LogoutListener() {
			public void onLogout(Widget widget) {
				MainApp.showWelcome();
			}

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
						add(new Label(date + ""));
					}
				};
				((ISmallAppAsync) UserEndPoint.create(GWT
						.create(ISmallApp.class)))
						.getLastSessionActivity(callback);
			}
		});

		add(activity);
	}

	public static void setAsCurrent() {
		new ExtSimple().run().setContent(new Workspace());
	}
}
