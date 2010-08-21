package org.gwtapp.extension.user.client.data;

import org.gwtapp.extension.user.client.data.metafield.ChallengeMetaField;
import org.gwtapp.extension.user.client.data.metafield.ResponseMetaField;

public interface ReCaptcha {

	public final static ResponseMetaField RESPONSE = new ResponseMetaField();
	public final static ChallengeMetaField CHALLENGE = new ChallengeMetaField();

	void setResponse(String response);

	String getResponse();

	void setChallenge(String challenge);

	String getChallenge();
}
