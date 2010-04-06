package org.gwtapp.startapp.rpc.ui;

import org.gwtapp.core.rpc.html.ui.form.HFieldPanel;
import org.gwtapp.core.rpc.html.ui.form.HFormPanel;
import org.gwtapp.startapp.rpc.data.Tab;
import org.gwtapp.startapp.rpc.data.TabModel;
import org.gwtapp.startapp.rpc.data.TabModelImpl;
import org.gwtapp.startapp.rpc.data.user.register.UserRegisterModel;
import org.gwtapp.startapp.rpc.ui.user.register.HUserRegisterPanel;

public class HTabPanel extends HFormPanel<TabModel> {

	private HFieldPanel<UserRegisterModel> userRegister = new HFieldPanel<UserRegisterModel>(
			Tab.USER_REGISTER.name(), "UserRegister:", new HUserRegisterPanel());

	public HTabPanel() {
		this(new TabModelImpl());
	}

	public HTabPanel(TabModel value) {
		super(value);
		addField(userRegister);
		setValue(value);
	}

	HFieldPanel<UserRegisterModel> getUserRegister() {
		return userRegister;
	}

}
