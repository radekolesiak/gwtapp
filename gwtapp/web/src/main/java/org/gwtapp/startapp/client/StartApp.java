package org.gwtapp.startapp.client;

import org.gwtapp.core.client.html.core.Deserializer;
import org.gwtapp.startapp.client.data.UserRegisterModel;
import org.gwtapp.startapp.client.ui.user.register.HUserRegisterPanel;
import org.gwtapp.startapp.client.ui.user.register.UserRegisterPanel;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.i18n.client.Dictionary;
import com.google.gwt.user.client.rpc.SerializationException;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;

public class StartApp implements EntryPoint {

	@Override
	public void onModuleLoad() {
		final UserRegisterPanel userRegisterPanel1 = new UserRegisterPanel();
		final UserRegisterPanel userRegisterPanel2 = new UserRegisterPanel();
		userRegisterPanel1
				.addValueChangeHandler(new ValueChangeHandler<UserRegisterModel>() {
					@Override
					public void onValueChange(
							ValueChangeEvent<UserRegisterModel> event) {
						userRegisterPanel2.setValue(event.getValue());
					}
				});
		userRegisterPanel2
				.addValueChangeHandler(new ValueChangeHandler<UserRegisterModel>() {
					@Override
					public void onValueChange(
							ValueChangeEvent<UserRegisterModel> event) {
						userRegisterPanel1.setValue(event.getValue());
					}
				});
		String serializedHWidget = Deserializer.decode(Dictionary
				.getDictionary(HWidgets.DICTIONARY).get(
						HWidgets.HUserRegisterPanel));
		try {
			HUserRegisterPanel modelPanel = (HUserRegisterPanel) Deserializer
					.getSerializedObject(serializedHWidget);
			final UserRegisterPanel urp = new UserRegisterPanel(modelPanel);
			final Button btn = new Button("Remove wrapped form");
			btn.addClickHandler(new ClickHandler() {
				@Override
				public void onClick(ClickEvent arg0) {
					btn.setEnabled(false);
					RootPanel.get().add(urp);
					RootPanel.get().remove(urp);
				}
			});
			RootPanel.get().add(btn);
		} catch (SerializationException e) {
			e.printStackTrace();
		}
		RootPanel.get().add(new Label(serializedHWidget));
		RootPanel.get().add(new Label("Form Panel 1:"));
		RootPanel.get().add(userRegisterPanel1);
		RootPanel.get().add(new Label("Form Panel 2:"));
		RootPanel.get().add(userRegisterPanel2);
	}
}
