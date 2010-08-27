package org.gwtapp.extension.user.client.data.metafield.recaptcha;

import org.gwtapp.core.rpc.data.MetaFieldAdapter;
import org.gwtapp.extension.user.client.data.ReCaptchaUser;

public class ResponseMetaField extends MetaFieldAdapter<ReCaptchaUser , String> {

	public ResponseMetaField() {
		super("response");
	}

	@Override
	public String get(ReCaptchaUser model) {
		return model.getResponse();
	}

	@Override
	public void set(ReCaptchaUser model, String value) {
		model.setResponse(value);
	}
}
