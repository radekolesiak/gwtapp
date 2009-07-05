package org.gwtapp.startapp.client;

import org.gwtapp.core.client.data.TestModel;
import org.gwtapp.startapp.client.data.UserRegister;
import org.gwtapp.startapp.client.ui.user.register.UserRegisterPanel;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;

public class StartApp implements EntryPoint {
	@Override
	public void onModuleLoad() {

		testGenerator();

		UserRegisterPanel userRegisterPanel = new UserRegisterPanel();
		userRegisterPanel
				.addValueChangeHandler(new ValueChangeHandler<UserRegister>() {
					@Override
					public void onValueChange(
							ValueChangeEvent<UserRegister> event) {
						System.out.println(event.getValue());
					}
				});
		UserRegister userRegister = new UserRegister();
		userRegister.setLogin("abc");
		// userRegisterPanel.setValue(userRegister);
		RootPanel.get().add(new Label("Hello world."));
		RootPanel.get().add(userRegisterPanel);
	}

	private void testGenerator() {
		TestModel test = GWT.create(TestModel.class);
		test.setText("aaa");
		System.out.println(test.getText());
		System.out.println(test.get(TestModel.TEXT));
		test.set(TestModel.TEXT, "bbb");
		System.out.println(test.getText());
		System.out.println(test.get(TestModel.TEXT));
	}
}
