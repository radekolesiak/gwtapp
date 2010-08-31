package org.gwtapp.startapp.client;

import org.gwtapp.extension.user.client.ReCaptchaUserRegisterServicePanel;
import org.gwtapp.extension.widget.client.ui.template.ListPanel;
import org.gwtapp.startapp.client.test.ListTestPanel;

import com.google.gwt.inject.client.GinModules;
import com.google.gwt.inject.client.Ginjector;

@GinModules(ClientModule.class)
public interface GinjectorService extends Ginjector {
	ReCaptchaUserRegisterServicePanel getReCaptchaUserRegisterServicePanel();
	ListPanel<ListTestPanel.Item> getListPanel();
}
