package org.gwtapp.extension.user.client.data.metafield.recaptcha;

import org.gwtapp.core.rpc.data.MetaFieldAdapter;
import org.gwtapp.extension.user.client.data.ReCaptchaUser;

public class PasswordVerifyMetaField extends
		MetaFieldAdapter<ReCaptchaUser, String> {

	public PasswordVerifyMetaField() {
		super("password-verify");
	}

	@Override
	public String get(ReCaptchaUser model) {
		return model.getPasswordVerify();
	}

	@Override
	public void set(ReCaptchaUser model, String value) {
		model.setPasswordVerify(value);
	}
}
