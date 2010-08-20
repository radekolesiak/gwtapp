package org.gwtapp.extension.user.client.data;

public interface ReCaptcha {

	void setResponse(String response);

	String getResponse();

	void setChallenge(String challenge);

	String getChallenge();
}
