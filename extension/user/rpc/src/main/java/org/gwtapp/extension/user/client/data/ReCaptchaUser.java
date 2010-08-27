package org.gwtapp.extension.user.client.data;

import org.gwtapp.extension.user.client.data.metafield.recaptcha.PasswordVerifyMetaField;

public interface ReCaptchaUser extends UserPassword, ReCaptcha {

	public final static PasswordVerifyMetaField PASSWORD_VERIFY = new PasswordVerifyMetaField();

	void setPasswordVerify(String passwordVerify);

	String getPasswordVerify();
}
