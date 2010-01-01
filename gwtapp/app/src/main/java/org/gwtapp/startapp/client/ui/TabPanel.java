package org.gwtapp.startapp.client.ui;

import org.gwtapp.core.client.html.ui.form.HFormPanel;
import org.gwtapp.core.client.ui.form.FormPanel;
import org.gwtapp.startapp.client.data.TabModel;


public class TabPanel extends FormPanel<TabModel> {

	public static class Style {
		public final static String TAB_PANEL="tabPanel";
	}
	
	public TabPanel(HFormPanel<TabModel> panel) {
		super(panel);
		addStyleName(Style.TAB_PANEL);
	}

}
