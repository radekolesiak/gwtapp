package org.gwtapp.extension.user.client.data;

@SuppressWarnings("serial")
public class ReCaptchaUserImpl extends UserPasswordImpl implements
		ReCaptchaUser {

	private String passwordVerify = PASSWORD_VERIFY.add(this).def();
	private String response = RESPONSE.add(this).def();
	private String challenge = CHALLENGE.add(this).def();

	public ReCaptchaUserImpl() {
	}

	public ReCaptchaUserImpl(String login, String email) {
		super(login, email);
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

	@Override
	public void setPasswordVerify(String passwordVerify) {
		this.passwordVerify = passwordVerify;
	}

	@Override
	public String getPasswordVerify() {
		return passwordVerify;
	}
}
