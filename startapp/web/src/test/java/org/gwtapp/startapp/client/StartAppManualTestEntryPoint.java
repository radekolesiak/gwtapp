package org.gwtapp.startapp.client;

import org.gwtapp.startapp.client.test.ReCaptchaTestPanel;
import org.gwtapp.template.client.TemplateRepository;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;

public class StartAppManualTestEntryPoint implements EntryPoint {

	public final static TemplateRepository template = new TemplateRepository(
			"/templates/");

	public final static GinjectorService gin = GWT
			.create(GinjectorService.class);

	@Override
	public final void onModuleLoad() {
		RootPanel.get().add(new Label("GWT Manual Testing"));
		RootPanel.get().add(new ReCaptchaTestPanel());
	}
}
