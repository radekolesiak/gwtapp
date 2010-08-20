package org.gwtapp.extension.user.client.data;

import org.gwtapp.extension.user.client.data.metafield.ReCaptchaMetaField;

@SuppressWarnings("serial")
public class ReCaptchaUser extends UserPassword {

	public final static ReCaptchaMetaField RECAPTCHA = new ReCaptchaMetaField();
	public final static ReCaptchaMetaField CHALLENGE = new ReCaptchaMetaField();

	public ReCaptchaUser() {
	}

	public ReCaptchaUser(String login, String email, String name) {
		super(login, email, name);
	}

	private String reCaptcha = RECAPTCHA.add(this).def();
	private String challenge = CHALLENGE.add(this).def();

	public void setReCaptcha(String reCaptcha) {
		this.reCaptcha = reCaptcha;
	}

	public String getReCaptcha() {
		return reCaptcha;
	}

	public void setChallenge(String challenge) {
		this.challenge = challenge;
	}

	public String getChallenge() {
		return challenge;
	}
}
