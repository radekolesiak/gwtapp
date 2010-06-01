package org.gwtapp.startapp.client.ui;

public class UserRegisterTab {

	private TabPanel tabPanel;

	public UserRegisterTab() {
		setTabPanel(new TabPanel());
	}

	private void setTabPanel(TabPanel tabPanel) {
		this.tabPanel = tabPanel;
	}

	public TabPanel getTabPanel() {
		return tabPanel;
	}
}
