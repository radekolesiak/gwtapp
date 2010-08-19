package org.gwtapp.startapp.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;

public class StartAppManualTestEntryPoint implements EntryPoint {

	@Override
	public final void onModuleLoad() {
		RootPanel.get().add(new Label("GWT Manual Testing"));
	}
}
