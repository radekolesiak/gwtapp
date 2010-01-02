package org.gwtapp.startapp.client.ui;

import org.gwtapp.core.client.html.core.HClient;
import org.gwtapp.startapp.client.HWidgets;
import org.gwtapp.startapp.client.data.TabModel;

import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.i18n.client.Dictionary;
import com.google.gwt.user.client.rpc.SerializationException;

public class UserRegisterTab {

	public UserRegisterTab() {
		String serializedTabPanel = HClient.decode(Dictionary.getDictionary(
				HWidgets.DICTIONARY).get(HWidgets.HTabPanel));
		try {
			HTabPanel hTabPanel = (HTabPanel) HClient
					.getSerializedObject(serializedTabPanel);
			final TabPanel tp = new TabPanel(hTabPanel);
			tp.addValueChangeHandler(new ValueChangeHandler<TabModel>() {
				@Override
				public void onValueChange(ValueChangeEvent<TabModel> arg0) {
					System.out.println("AAA");
				}
			});
		} catch (SerializationException e) {
			e.printStackTrace();
		}
	}
}
