package org.gwtapp.extension.user.client.data.metafield;

import org.gwtapp.core.rpc.data.MetaFieldAdapter;
import org.gwtapp.extension.user.client.data.ReCaptchaUserImpl;

public class ChallengeMetaField extends MetaFieldAdapter<ReCaptchaUserImpl, String> {

	public ChallengeMetaField() {
		super("challenge");
	}

	@Override
	public String get(ReCaptchaUserImpl model) {
		return model.getChallenge();
	}

	@Override
	public void set(ReCaptchaUserImpl model, String value) {
		model.setChallenge(value);
	}
}
