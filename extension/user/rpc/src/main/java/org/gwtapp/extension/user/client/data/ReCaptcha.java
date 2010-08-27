package org.gwtapp.extension.user.client.data;

import org.gwtapp.extension.user.client.data.metafield.recaptcha.ChallengeMetaField;
import org.gwtapp.extension.user.client.data.metafield.recaptcha.ResponseMetaField;

public interface ReCaptcha {

	public final static ResponseMetaField RESPONSE = new ResponseMetaField();
	public final static ChallengeMetaField CHALLENGE = new ChallengeMetaField();

	void setResponse(String response);

	String getResponse();

	void setChallenge(String challenge);

	String getChallenge();
}
