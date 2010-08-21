package org.gwtapp.startapp.client;

import org.gwtapp.core.client.AsyncCallbackGinjector;
import org.gwtapp.extension.user.client.ReCaptchaUserRegisterServicePanel;

import com.google.gwt.inject.client.GinModules;

@GinModules(ClientModule.class)
public interface GinjectorService extends AsyncCallbackGinjector {
	ReCaptchaUserRegisterServicePanel getReCaptchaUserRegisterServicePanel();
}
