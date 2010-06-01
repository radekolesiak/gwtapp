package org.gwtapp.startapp.client.ui;

import org.gwtapp.form.client.ui.FieldPanel;
import org.gwtapp.form.client.ui.FormPanel;
import org.gwtapp.startapp.client.ui.user.register.UserRegisterPanel;
import org.gwtapp.startapp.rpc.data.Tab;
import org.gwtapp.startapp.rpc.data.TabModel;
import org.gwtapp.startapp.rpc.data.TabModelImpl;
import org.gwtapp.startapp.rpc.data.user.register.UserRegisterModel;

public class TabPanel extends FormPanel<TabModel> {

	public static class Style {
		public final static String TAB_PANEL = "tabPanel";
	}

	private final FieldPanel<TabModel, UserRegisterModel> userRegister = new FieldPanel<TabModel, UserRegisterModel>(
			this, Tab.USER_REGISTER.name(), "", new UserRegisterPanel());

	public TabPanel() {
		super(new TabModelImpl());
		addStyleName(Style.TAB_PANEL);
		setFireOnUpdate(true);
		addField(userRegister);

	}

	public UserRegisterModel getUserRegisterModel() {
		return userRegister.getValue();
	}

	public void setUserRegisterModel(UserRegisterModel userRegisterModel) {
		userRegister.setValue(userRegisterModel);
	}
}
