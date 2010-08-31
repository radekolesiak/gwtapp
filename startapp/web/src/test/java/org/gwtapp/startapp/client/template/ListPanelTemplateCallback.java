package org.gwtapp.startapp.client.template;

import org.gwtapp.startapp.client.StartAppManualTestEntryPoint;
import org.gwtapp.template.client.callback.TemplateCallbackAdapter;

public class ListPanelTemplateCallback extends TemplateCallbackAdapter {
	public ListPanelTemplateCallback() {
		super(StartAppManualTestEntryPoint.template.load("ListPanel.jsp"));
	}
}
