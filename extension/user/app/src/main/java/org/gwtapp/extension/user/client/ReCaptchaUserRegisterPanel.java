package org.gwtapp.extension.user.client;

import org.gwtapp.extension.user.client.data.ReCaptchaUser;
import org.gwtapp.form.client.ui.TemplateModelPanel;
import org.gwtapp.template.client.handler.TextBoxHandler;
import org.gwtapp.template.client.handler.WidgetHandler;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.user.client.Element;

public class ReCaptchaUserRegisterPanel extends TemplateModelPanel<ReCaptchaUser> {

	private final WidgetHandler reCaptcha = new WidgetHandler();

	public ReCaptchaUserRegisterPanel(TemplateCallback callback) {
		super(callback);
		add(ReCaptchaUser.LOGIN, new TextBoxHandler());
		add(ReCaptchaUser.EMAIL, new TextBoxHandler());
		add(ReCaptchaUser.NAME, new TextBoxHandler());
		add("reCaptchaContainer", reCaptcha);
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
		showReCaptcha(element, createCallback(callback));
	}

	protected void onReCaptchaLoaded() {
		fireChangeEvent();
	}

	protected native void showReCaptcha(Element element,
			JavaScriptObject callback)/*-{
										$wnd.showReCaptcha(element, callback);
										}-*/;

	protected native JavaScriptObject createCallback(
			ReCaptchaCallback callback)/*-{
												return function(){
												callback.@org.gwtapp.extension.user.client.ReCaptchaCallback::onCallback()();										
												}
												}-*/;
}
