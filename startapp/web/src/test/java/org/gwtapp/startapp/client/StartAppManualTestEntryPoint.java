package org.gwtapp.startapp.client;

import org.gwtapp.extension.user.client.ReCaptchaUserPanel;
import org.gwtapp.template.client.TemplateRepository;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;

public class StartAppManualTestEntryPoint implements EntryPoint {

	public final static TemplateRepository template = new TemplateRepository(
			"/templates/");

	@Override
	public final void onModuleLoad() {
		RootPanel.get().add(new Label("GWT Manual Testing"));
		ReCaptchaUserPanel reCaptcha = new ReCaptchaUserPanel(template
				.load("user/ReCaptchaUserPanel.jsp"));
		RootPanel.get().add(reCaptcha);
		reCaptcha.addChangeHandler(new ChangeHandler() {
			@Override
			public void onChange(ChangeEvent event) {
				Window.alert("CAPTCHA");
			}
		});
	}
}
