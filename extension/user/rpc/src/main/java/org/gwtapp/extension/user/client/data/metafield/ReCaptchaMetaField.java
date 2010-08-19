package org.gwtapp.extension.user.client.data.metafield;

import org.gwtapp.core.rpc.data.MetaFieldAdapter;
import org.gwtapp.extension.user.client.data.ReCaptchaUser;

public class ReCaptchaMetaField extends MetaFieldAdapter<ReCaptchaUser, String> {

	public ReCaptchaMetaField() {
		super("recaptcha");
	}

	@Override
	public String get(ReCaptchaUser model) {
		return model.getReCaptcha();
	}

	@Override
	public void set(ReCaptchaUser model, String value) {
		model.setReCaptcha(value);
	}
}
