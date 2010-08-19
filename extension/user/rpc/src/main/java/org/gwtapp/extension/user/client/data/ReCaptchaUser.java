package org.gwtapp.extension.user.client.data;

import javax.persistence.Transient;

import org.gwtapp.extension.user.client.data.metafield.ReCaptchaMetaField;

@SuppressWarnings("serial")
public class ReCaptchaUser extends User {

	public final static ReCaptchaMetaField RECAPTCHA = new ReCaptchaMetaField();

	public ReCaptchaUser() {
	}

	public ReCaptchaUser(String login, String email, String name) {
		super(login, email, name);
	}

	@Transient
	private String reCaptcha = RECAPTCHA.add(this).def();

	public void setReCaptcha(String reCaptcha) {
		this.reCaptcha = reCaptcha;
	}

	public String getReCaptcha() {
		return reCaptcha;
	}
}
