package org.gwtapp.startapp.client.panel;

import org.gwtapp.extension.user.client.UserPanel;
import org.gwtapp.startapp.client.StartAppManualTestEntryPoint;

public class UserPanelBinder extends UserPanel {
	public UserPanelBinder() {
		super(StartAppManualTestEntryPoint.template.load("user/UserPanel.jsp"));
	}
}
