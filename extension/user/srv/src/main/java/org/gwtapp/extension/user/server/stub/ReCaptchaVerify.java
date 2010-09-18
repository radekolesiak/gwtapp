package org.gwtapp.extension.user.server.stub;

import org.gwtapp.extension.user.client.data.ReCaptcha;

public interface ReCaptchaVerify {
	boolean verify(ReCaptcha reCaptcha, String privateKey, String remoteIP);
}
