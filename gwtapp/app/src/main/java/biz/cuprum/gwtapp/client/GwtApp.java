package biz.cuprum.gwtapp.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;

public class GwtApp implements EntryPoint {
	@Override
	public void onModuleLoad() {
		RootPanel.get().add(new Label("Hello world."));
	}
}
