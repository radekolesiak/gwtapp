package biz.cuprum.gwtapp.client.ui.user.register;

import biz.cuprum.gwtapp.client.data.UserRegister;
import biz.cuprum.gwtapp.core.client.ui.FieldPanel;

import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HasName;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.TextBox;

public class UserRegisterPanel extends FlowPanel implements HasName,
		HasValue<UserRegister> {

	private String name;
	private UserRegister userRegister = new UserRegister();

	public UserRegisterPanel() {
		FieldPanel<String> login = new FieldPanel<String>("Login:",
				new TextBox());
		FieldPanel<String> email = new FieldPanel<String>("Email:",
				new TextBox());
		FieldPanel<String> password = new FieldPanel<String>("Password:",
				new TextBox());
		login.addValueChangeHandler(new ValueChangeHandler<String>() {
			@Override
			public void onValueChange(ValueChangeEvent<String> event) {
				userRegister.setLogin(event.getValue());
				ValueChangeEvent.fire(UserRegisterPanel.this, getValue());
			}
		});
		email.addValueChangeHandler(new ValueChangeHandler<String>() {
			@Override
			public void onValueChange(ValueChangeEvent<String> event) {
				userRegister.setEmail(event.getValue());
				ValueChangeEvent.fire(UserRegisterPanel.this, getValue());
			}
		});
		password.addValueChangeHandler(new ValueChangeHandler<String>() {
			@Override
			public void onValueChange(ValueChangeEvent<String> event) {
				userRegister.setPassword(event.getValue());
				ValueChangeEvent.fire(UserRegisterPanel.this, getValue());
			}
		});
		add(login);
		add(email);
		add(password);
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public UserRegister getValue() {
		return userRegister;
	}

	@Override
	public void setValue(UserRegister userRegister) {
		setValue(userRegister, false);
	}

	@Override
	public void setValue(UserRegister userRegister, boolean fireEvents) {
		if (userRegister == null) {
			throw new IllegalArgumentException("value must not be null");
		}
		this.userRegister = userRegister;
		if (fireEvents) {
			ValueChangeEvent.fire(this, userRegister);
		}
	}

	@Override
	public HandlerRegistration addValueChangeHandler(
			ValueChangeHandler<UserRegister> handler) {
		return addHandler(handler, ValueChangeEvent.getType());
	}

}
