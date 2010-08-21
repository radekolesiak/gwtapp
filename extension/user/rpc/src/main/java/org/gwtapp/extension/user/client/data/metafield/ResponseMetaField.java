package org.gwtapp.extension.user.client.data.metafield;

import org.gwtapp.core.rpc.data.MetaFieldAdapter;
import org.gwtapp.extension.user.client.data.ReCaptchaUserImpl;

public class ResponseMetaField extends MetaFieldAdapter<ReCaptchaUserImpl, String> {

	public ResponseMetaField() {
		super("response");
	}

	@Override
	public String get(ReCaptchaUserImpl model) {
		return model.getResponse();
	}

	@Override
	public void set(ReCaptchaUserImpl model, String value) {
		model.setResponse(value);
	}
}
