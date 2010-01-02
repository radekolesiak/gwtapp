package org.gwtapp.startapp.client.ui;

import org.gwtapp.core.client.html.ui.form.HFieldPanel;
import org.gwtapp.core.client.ui.form.FieldPanel;
import org.gwtapp.core.client.ui.form.FormPanel;
import org.gwtapp.startapp.client.data.Tab;
import org.gwtapp.startapp.client.data.TabModel;
import org.gwtapp.startapp.client.data.user.register.UserRegisterModel;
import org.gwtapp.startapp.client.ui.user.register.HUserRegisterPanel;
import org.gwtapp.startapp.client.ui.user.register.UserRegisterPanel;

public class TabPanel extends FormPanel<TabModel> {

	public static class Style {
		public final static String TAB_PANEL = "tabPanel";
	}

	private final FieldPanel<TabModel, UserRegisterModel> userRegister;

	public TabPanel(HTabPanel modelPanel) {
		super(modelPanel);
		addStyleName(Style.TAB_PANEL);
		setFireOnUpdate(true);

		HFieldPanel<UserRegisterModel> urf = modelPanel
				.getField(Tab.USER_REGISTER.name());
		HUserRegisterPanel hUserRegisterPanel = urf.getController();
		UserRegisterPanel urp = new UserRegisterPanel(hUserRegisterPanel);
		userRegister = new FieldPanel<TabModel, UserRegisterModel>(this, urf,
				urp);
		urp.setFireOnUpdate(true);

		wrap();
	}
	
	public UserRegisterModel getUserRegisterModel(){
		return userRegister.getValue();
	}
	
	public void setUserRegisterModel(UserRegisterModel userRegisterModel){
		userRegister.setValue(userRegisterModel);
	}
}
