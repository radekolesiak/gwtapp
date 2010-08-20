package org.gwtapp.extension.user.client.data;

import org.gwtapp.extension.user.client.data.metafield.ChallengeMetaField;
import org.gwtapp.extension.user.client.data.metafield.ResponseMetaField;

@SuppressWarnings("serial")
public class ReCaptchaUser extends UserPassword implements ReCaptcha {

	public final static ResponseMetaField RESPONSE = new ResponseMetaField();
	public final static ChallengeMetaField CHALLENGE = new ChallengeMetaField();

	private String response = RESPONSE.add(this).def();
	private String challenge = CHALLENGE.add(this).def();

	public ReCaptchaUser() {
	}

	public ReCaptchaUser(String login, String email, String name) {
		super(login, email, name);
	}

	@Override
	public void setResponse(String response) {
		this.response = response;
	}

	@Override
	public String getResponse() {
		return response;
	}

	@Override
	public void setChallenge(String challenge) {
		this.challenge = challenge;
	}

	@Override
	public String getChallenge() {
		return challenge;
	}
}
