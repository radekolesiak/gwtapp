package com.cuprum.web.smallapp.mainapp.client.page;

import com.cuprum.web.common.client.EndPoint;
import com.cuprum.web.common.client.WebCallback;
import com.cuprum.web.templates.simple.client.Simple;
import com.cuprum.web.widgets.user.register.client.stub.IUserRegister;
import com.cuprum.web.widgets.user.register.client.stub.IUserRegisterAsync;
import com.extjs.gxt.ui.client.widget.form.LabelField;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.VerticalPanel;

public class ConfirmPage extends VerticalPanel {
	private final WebCallback<Boolean> callback = new WebCallback<Boolean>() {
		@Override
		public void onResponseSuccess(Boolean result) {
			add(new LabelField("Result: " + result));
		}
	};

	private final IUserRegisterAsync endPoint = ((IUserRegisterAsync) EndPoint
			.create(GWT.create(IUserRegister.class)));

	public ConfirmPage() {
		add(new LabelField("Confirm"));
		endPoint.confirm(Window.Location.getParameter("confirm"), callback);
	}

	public static void setAsCurrent() {
		new Simple().run().setContent(new ConfirmPage());
	}
}
