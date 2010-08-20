package org.gwtapp.startapp.client;

import org.gwtapp.extension.user.client.ReCaptchaUserRegisterServicePanel;

import com.google.gwt.inject.client.GinModules;
import com.google.gwt.inject.client.Ginjector;

@GinModules(ClientModule.class)
public interface GIN extends Ginjector {
	ReCaptchaUserRegisterServicePanel getReCaptchaUserRegisterServicePanel();
}
