package com.cuprum.web.smallapp.mainapp.client.page;

import com.cuprum.web.common.client.Application;
import com.cuprum.web.common.client.EndPoint;
import com.cuprum.web.common.client.Util;
import com.cuprum.web.common.client.WebCallback;
import com.cuprum.web.widgets.user.register.client.UserRegister;
import com.cuprum.web.widgets.user.register.client.stub.IUserRegister;
import com.cuprum.web.widgets.user.register.client.stub.IUserRegisterAsync;
import com.extjs.gxt.ui.client.event.ComponentEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.widget.Viewport;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.LabelField;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;

public class ConfirmPage extends Viewport {
	private final WebCallback<Boolean> callback = new WebCallback<Boolean>() {
		@Override
		public void onResponseSuccess(Boolean result) {
			add(new LabelField("Result: " + result));
			String description = "";
			if (result) {
				description = "Go to login on main page.";
			} else {
				description = "Go to main page.";
			}
			Button redirect = new Button(description);
			redirect
					.addSelectionListener(new SelectionListener<ComponentEvent>() {
						@Override
						public void componentSelected(ComponentEvent ce) {
							Window.Location.replace(Util.getUrl());
						}
					});
			add(redirect);
		}
	};

	private final IUserRegisterAsync endPoint = ((IUserRegisterAsync) EndPoint
			.create(GWT.create(IUserRegister.class)));

	public ConfirmPage() {
		endPoint.confirm(Window.Location
				.getParameter(UserRegister.CONFIRM_REQUEST), callback);
	}

	public static void setAsCurrent() {
		Application.setPage(new ConfirmPage());
	}
}
