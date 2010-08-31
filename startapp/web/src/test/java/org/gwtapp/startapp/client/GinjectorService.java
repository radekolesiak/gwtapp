package org.gwtapp.startapp.client;

import org.gwtapp.extension.user.client.ReCaptchaUserRegisterServicePanel;
import org.gwtapp.extension.user.client.UserPanel;
import org.gwtapp.extension.widget.client.ui.template.ListPanel;
import org.gwtapp.startapp.client.test.ListPanelTestManual;

import com.google.gwt.inject.client.GinModules;
import com.google.gwt.inject.client.Ginjector;

@GinModules(ClientModule.class)
public interface GinjectorService extends Ginjector {
	UserPanel getUserPanel();
	ReCaptchaUserRegisterServicePanel getReCaptchaUserRegisterServicePanel();
	ListPanel<ListPanelTestManual.Item> getListPanel();
}
