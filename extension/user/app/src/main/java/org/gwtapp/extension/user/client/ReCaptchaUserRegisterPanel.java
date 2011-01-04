package org.gwtapp.extension.user.client;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import org.gwtapp.extension.user.client.data.ReCaptchaUser;
import org.gwtapp.form.client.ui.TemplateModelPanel;
import org.gwtapp.template.client.handler.PasswordTextBoxHandler;
import org.gwtapp.template.client.handler.UiHandler;
import org.gwtapp.template.client.handler.WidgetHandler;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.Element;
import com.google.inject.BindingAnnotation;
import com.google.inject.Inject;

public class ReCaptchaUserRegisterPanel extends
		TemplateModelPanel<ReCaptchaUser> {

	@Retention(RetentionPolicy.RUNTIME)
	@BindingAnnotation
	public static @interface Bind {
	}

	public static class Provider {
		@Inject
		@Bind
		public TemplateCallback callback;
		@Inject
		@Bind
		public UserPanel userPanel;
		@Inject
		@Bind
		public ReCaptchaUser value;
	}

	public static enum State {
		RECAPTCHA_LOADED, REGISTER_BUTTON_CLICKED
	}

	private final HandlerManager stateHandlerManager = new HandlerManager(this);

	private final WidgetHandler reCaptcha = new WidgetHandler();
	private final WidgetHandler register = new WidgetHandler();
	private final WidgetHandler indicator = new WidgetHandler();

	private boolean inProgress = false;

	@Inject
	public ReCaptchaUserRegisterPanel(Provider provider) {
		super(provider.callback, provider.value);
		setAutoValueChangeFire(false);
		add(ReCaptchaUser.PASSWORD, new PasswordTextBoxHandler());
		add(ReCaptchaUser.PASSWORD_VERIFY, new PasswordTextBoxHandler());
		add(ReCaptchaUser.USER, new UiHandler<UserPanel>(provider.userPanel));
		add("reCaptchaContainer", reCaptcha);
		add("register", register);
		add("indicator", indicator);
	}

	@Override
	public void onFormWidgets() {
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
				fireStateChanged(State.REGISTER_BUTTON_CLICKED);
			}
		});
		showReCaptcha(element, createCallback(callback));
		setInProgress(isInProgress());
	}

	@Override
	public ReCaptchaUser getValue() {
		ReCaptchaUser value = super.getValue();
		value.setResponse(ReCaptchaPanel.getResponse());
		value.setChallenge(ReCaptchaPanel.getChallenge());
		return value;
	}

	public void setInProgress(boolean inProgress) {
		this.inProgress = inProgress;
		if (isTemplated()) {
			setEnabled(!inProgress);
			indicator.getWidget().setVisible(inProgress);
		}
	}

	public boolean isInProgress() {
		return inProgress;
	}

	public HandlerRegistration addStateChangedHandler(
			ValueChangeHandler<State> handler) {
		return stateHandlerManager.addHandler(ValueChangeEvent.getType(),
				handler);
	}

	protected void onReCaptchaLoaded() {
		String reCaptchaStyle = register.getMessage("reCaptchaStyle");
		if (!reCaptchaStyle.isEmpty()) {
			addStyleName(reCaptchaStyle);
		}
		fireStateChanged(State.RECAPTCHA_LOADED);
	}

	protected void fireStateChanged(State state) {
		stateHandlerManager.fireEvent(new TemplatePanelValueChangeEvent<State>(
				state));
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
