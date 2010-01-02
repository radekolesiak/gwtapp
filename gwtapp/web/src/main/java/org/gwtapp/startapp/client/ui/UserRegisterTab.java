package org.gwtapp.startapp.client.ui;

import org.gwtapp.core.client.html.core.HClient;
import org.gwtapp.startapp.client.HWidgets;

import com.google.gwt.i18n.client.Dictionary;
import com.google.gwt.user.client.rpc.SerializationException;

public class UserRegisterTab {

	private TabPanel tabPanel;
	
	public UserRegisterTab() {
		String serializedTabPanel = HClient.decode(Dictionary.getDictionary(
				HWidgets.DICTIONARY).get(HWidgets.HTabPanel));
		try {
			HTabPanel hTabPanel = (HTabPanel) HClient
					.getSerializedObject(serializedTabPanel);
			final TabPanel tp = new TabPanel(hTabPanel);
			setTabPanel(tp);
		} catch (SerializationException e) {
			e.printStackTrace();
		}
	}

	private void setTabPanel(TabPanel tabPanel) {
		this.tabPanel = tabPanel;
	}

	public TabPanel getTabPanel() {
		return tabPanel;
	}
}
