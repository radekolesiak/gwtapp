package biz.cuprum.gwtapp.client;

import biz.cuprum.gwtapp.client.data.UserRegister;
import biz.cuprum.gwtapp.client.ui.user.register.UserRegisterPanel;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;

public class GwtApp implements EntryPoint {
	@Override
	public void onModuleLoad() {
		UserRegisterPanel userRegisterPanel = new UserRegisterPanel();
		userRegisterPanel.addValueChangeHandler(new ValueChangeHandler<UserRegister>(){
			@Override
			public void onValueChange(ValueChangeEvent<UserRegister> event) {
				System.out.println(event.getValue());
			}
		});
		UserRegister userRegister = new UserRegister();
		userRegister.setLogin("abc");
		//userRegisterPanel.setValue(userRegister);
		RootPanel.get().add(new Label("Hello world."));
		RootPanel.get().add(userRegisterPanel);
	}
}
