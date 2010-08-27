package org.gwtapp.extension.user.client;

import org.gwtapp.extension.user.client.data.ReCaptchaUser;
import org.gwtapp.form.client.ui.TemplateModelPanel;
import org.gwtapp.template.client.UiHandler;
import org.gwtapp.template.client.handler.PasswordTextBoxHandler;
import org.gwtapp.template.client.handler.WidgetHandler;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Element;
import com.google.inject.Inject;
import com.google.inject.name.Named;

public class ReCaptchaUserRegisterPanel extends
		TemplateModelPanel<ReCaptchaUser> {

	public static final String BIND_NAME = "ReCaptchaUserRegisterServicePanel";

	private final WidgetHandler reCaptcha = new WidgetHandler();
	private final WidgetHandler register = new WidgetHandler();

	@Inject
	public ReCaptchaUserRegisterPanel(
			@Named(BIND_NAME) TemplateCallback callback, UserPanel userPanel) {
		super(callback);
		setAutoValueChangeFire(false);
		add(ReCaptchaUser.PASSWORD, new PasswordTextBoxHandler());
		add(ReCaptchaUser.PASSWORD_VERIFY, new PasswordTextBoxHandler());
		add(ReCaptchaUser.USER, new UiHandler<UserPanel>(userPanel));
		add("reCaptchaContainer", reCaptcha);
		add("register", register);
	}

	@Override
	public void onAddFormWidgets() {
		Element element = reCaptcha.getWidget().getElement();
		ReCaptchaCallback callback = new ReCaptchaCallback() {
			@Override
			public void onCallback() {
				onReCaptchaLoaded();
			}
		};
		register.getWidget().addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				fireValueChangeEvent();
			}
		});
		showReCaptcha(element, createCallback(callback));
	}

	@Override
	public ReCaptchaUser getValue() {
		ReCaptchaUser value = super.getValue();
		value.setResponse(ReCaptchaPanel.getResponse());
		value.setChallenge(ReCaptchaPanel.getChallenge());
		return value;
	}

	protected void onReCaptchaLoaded() {
		String reCaptchaStyle = register.getMessage("reCaptchaStyle");
		if (!reCaptchaStyle.isEmpty()) {
			addStyleName(reCaptchaStyle);
		}
		fireChangeEvent();
	}

	protected native void showReCaptcha(Element element,
			JavaScriptObject callback)/*-{
										$wnd.showReCaptcha(element, callback);
										}-*/;

	protected native JavaScriptObject createCallback(ReCaptchaCallback callback)/*-{
																				return function(){
																				callback.@org.gwtapp.extension.user.client.ReCaptchaCallback::onCallback()();										
																				}
																				}-*/;
}
