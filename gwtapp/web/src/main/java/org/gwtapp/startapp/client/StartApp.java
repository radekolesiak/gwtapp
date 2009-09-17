package org.gwtapp.startapp.client;

import org.gwtapp.core.client.html.core.Deserializer;
import org.gwtapp.startapp.client.data.UserRegisterModel;
import org.gwtapp.startapp.client.ui.user.register.HUserRegisterPanel;
import org.gwtapp.startapp.client.ui.user.register.UserRegisterPanel;
import org.gwtapp.startapp.client.ui.user.register.UserRegisterSubmitPanel;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.i18n.client.Dictionary;
import com.google.gwt.user.client.rpc.SerializationException;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;

public class StartApp implements EntryPoint {

	@Override
	public void onModuleLoad() {
		UserRegisterPanel userRegisterPanel = new UserRegisterPanel();
		UserRegisterSubmitPanel userRegisterSubmitPanel = new UserRegisterSubmitPanel();
		userRegisterPanel
				.addValueChangeHandler(new ValueChangeHandler<UserRegisterModel>() {
					@Override
					public void onValueChange(
							ValueChangeEvent<UserRegisterModel> event) {
						System.out.println(event.getValue());
					}
				});
		userRegisterSubmitPanel
				.addValueChangeHandler(new ValueChangeHandler<UserRegisterModel>() {
					@Override
					public void onValueChange(
							ValueChangeEvent<UserRegisterModel> event) {
						System.out.println(event.getValue());
					}
				});
		String[] widget = Dictionary.getDictionary("HWidget").get("HWidget")
				.split(",");
		String s = "";
		for (String w : widget) {
			s += (char) Byte.parseByte(w);
		}
		try {
			HUserRegisterPanel modelPanel = (HUserRegisterPanel) Deserializer
					.getSerializedObject(s);
			final UserRegisterPanel urp = new UserRegisterPanel(
					modelPanel);
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
		RootPanel.get().add(new Label(s));
		RootPanel.get().add(new Label("Form Panel:"));
		RootPanel.get().add(userRegisterPanel);
		RootPanel.get().add(new Label("Submit Form Panel:"));
		RootPanel.get().add(userRegisterSubmitPanel);
	}
}
