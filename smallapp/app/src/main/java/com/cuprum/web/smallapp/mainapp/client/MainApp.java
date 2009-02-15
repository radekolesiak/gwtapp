package com.cuprum.web.smallapp.mainapp.client;

import com.cuprum.web.common.client.Application;
import com.cuprum.web.common.client.SessionEntryPoint;
import com.cuprum.web.common.client.SessionNotFoundListener;
import com.cuprum.web.smallapp.mainapp.client.i18n.InfoMessages;
import com.cuprum.web.smallapp.mainapp.client.page.ConfirmPage;
import com.cuprum.web.smallapp.mainapp.client.page.RemindPasswordPage;
import com.cuprum.web.smallapp.mainapp.client.page.WelcomePage;
import com.cuprum.web.smallapp.mainapp.client.page.WorkspacePage;
import com.cuprum.web.widgets.user.password.client.ChangePasswordByToken;
import com.cuprum.web.widgets.user.register.client.UserRegister;
import com.extjs.gxt.ui.client.widget.Info;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

/**
 * Entry point classes define <code>onModuleLoad()</code>. GWT modules
 * tutorial: http://developerlife.com/tutorials/?p=229
 * 
 * @author Radek Olesiak
 */

public class MainApp extends SessionEntryPoint {
	static SmallappViewport view = new SmallappViewport();

	@Override
	public final void onSessionModuleLoad() {
		if (Window.Location.getParameter(UserRegister.CONFIRM_REQUEST) != null) {
			showConfirm();
		} else if (Window.Location
				.getParameter(ChangePasswordByToken.REMIND_REQUEST) != null) {
			showRemindPassword();
		} else {
			showWelcome();
		}
	}

	@Override
	public final String getModuleName() {
		return "smallapp";
	}

	@Override
	public final void onSessionModuleError(final Throwable caught) {
		setPage(new Label(caught.getMessage()));
	}

	public static void setPage(Widget page) {
		view.setContent(page);
		Application.setPage(view);
	}

	public static void showWelcome() {
		setPage(new WelcomePage());
	}

	public static void showWorkspace() {
		setPage(new WorkspacePage());
	}

	public static void showConfirm() {
		setPage(new ConfirmPage());
	}

	public static void showRemindPassword() {
		setPage(new RemindPasswordPage());
	}

	public static void defaultOnSessionNotFound() {
		InfoMessages messages = GWT.create(InfoMessages.class);
		Info.display(messages.msgSessionNotFoundTitle(), messages
				.msgSessionNotFound(), new String[] {});
	}

	private final static SessionNotFoundListener sessionNotFoundListener = new SessionNotFoundListener() {
		public void onSessionNotFound(Widget widget) {
			defaultOnSessionNotFound();
		}
	};

	public static SessionNotFoundListener getDefaultSessionNotFoundListener() {
		return sessionNotFoundListener;
	}
}
