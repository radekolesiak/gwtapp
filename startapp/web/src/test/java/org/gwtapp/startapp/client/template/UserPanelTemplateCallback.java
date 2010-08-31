package org.gwtapp.startapp.client.template;

import org.gwtapp.startapp.client.StartAppManualTestEntryPoint;
import org.gwtapp.template.client.callback.TemplateCallbackAdapter;

public class UserPanelTemplateCallback extends TemplateCallbackAdapter {
	public UserPanelTemplateCallback() {
		super(StartAppManualTestEntryPoint.template.load("user/UserPanel.jsp"));
	}
}
