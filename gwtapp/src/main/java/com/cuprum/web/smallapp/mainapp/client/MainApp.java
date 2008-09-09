package com.cuprum.web.smallapp.mainapp.client;

import com.cuprum.web.common.client.SessionEntryPoint;
import com.cuprum.web.common.client.Util;
import com.cuprum.web.common.client.data.TUserSession;
import com.cuprum.web.smallapp.mainapp.client.page.ConfirmPage;
import com.cuprum.web.smallapp.mainapp.client.page.WelcomePage;
import com.cuprum.web.templates.simple.client.Simple;
import com.cuprum.web.widgets.user.register.client.Constants;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Label;

/**
 * Entry point classes define <code>onModuleLoad()</code>. GWT modules
 * tutorial: http://developerlife.com/tutorials/?p=229
 * 
 * @author Radek Olesiak
 */

public class MainApp extends SessionEntryPoint {
	@Override
	public final void onSessionModuleLoad() {
		Util.enableDebug();
 		String confirm = Window.Location.getParameter(Constants.CONFIRM_REQUEST);
		if (confirm != null) {
			showConfirm();
		} else {
			showWelcome();
		}
	}

	@Override
	public final String getModuleName() {
		return "smallapp";
	}

	@Override
	public final void onSessionModuleError() {
		new Simple().run().setContent(new Label("Error"));
	}

	public static void showWelcome() {
		TUserSession.setSession(null);
		WelcomePage.setAsCurrent();
	}

	public static void showConfirm() {
		TUserSession.setSession(null);
		ConfirmPage.setAsCurrent();
	}

}
