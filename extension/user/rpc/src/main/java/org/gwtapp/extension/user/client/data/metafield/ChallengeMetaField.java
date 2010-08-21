package org.gwtapp.extension.user.client.data.metafield;

import org.gwtapp.core.rpc.data.MetaFieldAdapter;
import org.gwtapp.extension.user.client.data.ReCaptchaUser;

public class ChallengeMetaField extends MetaFieldAdapter<ReCaptchaUser, String> {

	public ChallengeMetaField() {
		super("challenge");
	}

	@Override
	public String get(ReCaptchaUser model) {
		return model.getChallenge();
	}

	@Override
	public void set(ReCaptchaUser model, String value) {
		model.setChallenge(value);
	}
}
