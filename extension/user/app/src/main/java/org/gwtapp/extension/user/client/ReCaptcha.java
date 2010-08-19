package org.gwtapp.extension.user.client;

import com.google.gwt.dom.client.InputElement;
import com.google.gwt.user.client.DOM;

public class ReCaptcha {

	public native final static void reload()/*-{
											$wnd.Recaptcha.reload();
											}-*/;

	public final static String getChallenge() {
		return getValue("recaptcha_challenge_field");
	}

	public final static String getResponse() {
		return getValue("recaptcha_response_field");
	}

	private static String getValue(String id) {
		return InputElement.as(DOM.getElementById(id)).getValue();
	}
}
