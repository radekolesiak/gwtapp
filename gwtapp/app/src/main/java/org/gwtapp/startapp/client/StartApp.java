package org.gwtapp.startapp.client;

import org.gwtapp.startapp.client.data.UserRegisterModel;
import org.gwtapp.startapp.client.ui.user.register.UserRegisterPanel;
import org.gwtapp.startapp.client.ui.user.register.UserRegisterSubmitPanel;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;

public class StartApp implements EntryPoint {
	@Override
	public void onModuleLoad() {
		UserRegisterPanel userRegisterPanel = new UserRegisterPanel();
		UserRegisterSubmitPanel userRegisterSubmitPanel = new UserRegisterSubmitPanel();
		userRegisterPanel
				.addValueChangeHandler(new ValueChangeHandler<UserRegisterModel>() {
					@Override
					public void onValueChange(
							ValueChangeEvent<UserRegisterModel> event) {
						System.out.println(event.getValue());
					}
				});
		userRegisterSubmitPanel
				.addValueChangeHandler(new ValueChangeHandler<UserRegisterModel>() {
					@Override
					public void onValueChange(
							ValueChangeEvent<UserRegisterModel> event) {
						System.out.println(event.getValue());
					}
				});
		RootPanel.get().add(new Label("Form Panel:"));
		RootPanel.get().add(userRegisterPanel);
		RootPanel.get().add(new Label("Submit Form Panel:"));
		RootPanel.get().add(userRegisterSubmitPanel);
	}
}
