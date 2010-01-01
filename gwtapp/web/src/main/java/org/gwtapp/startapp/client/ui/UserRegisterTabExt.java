package org.gwtapp.startapp.client.ui;

import java.util.Date;

import org.gwtapp.core.client.html.core.HClient;
import org.gwtapp.startapp.client.HWidgets;
import org.gwtapp.startapp.client.StartApp;
import org.gwtapp.startapp.client.data.user.register.UserRegisterModel;
import org.gwtapp.startapp.client.ui.user.register.HUserRegisterPanel;
import org.gwtapp.startapp.client.ui.user.register.UserRegisterPanel;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.i18n.client.Dictionary;
import com.google.gwt.user.client.rpc.SerializationException;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;

public class UserRegisterTabExt {

	private RootPanel root;

	public UserRegisterTabExt() {
		String serializedUserRegisterPanel = HClient.decode(Dictionary
				.getDictionary(HWidgets.DICTIONARY).get(
						HWidgets.HUserRegisterPanel));
		try {
			HUserRegisterPanel hUserRegisterPanel = (HUserRegisterPanel) HClient
					.getSerializedObject(serializedUserRegisterPanel);
			final UserRegisterPanel urp = new UserRegisterPanel(
					hUserRegisterPanel);

			String id = urp.getElement().getParentElement().getAttribute("id");
			if (id == null || id.isEmpty()) {
				id = Math.random() + ":" + new Date().getTime();
				urp.getElement().getParentElement().setAttribute("id", id);
			}
			root = RootPanel.get(id);

			final Button btn = new Button("Remove wrapped form");
			btn.addClickHandler(new ClickHandler() {
				@Override
				public void onClick(ClickEvent event) {
					btn.setEnabled(false);
					root.remove(urp);
				}
			});
			
			root.add(new Label("Form Panel 1:"));
			root.add(urp); // reattach and add logicaly 
			root.add(btn);			
			ui(urp);
		} catch (SerializationException e) {
			e.printStackTrace();
		}
	}

	private void ui(final UserRegisterPanel userRegisterPanel1) {
		final UserRegisterPanel userRegisterPanel2 = new UserRegisterPanel();
		userRegisterPanel1.setFireOnUpdate(true);
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
		root.add(new Button("Register 1:", new ClickHandler() {
			@Override
			public void onClick(ClickEvent arg0) {
				StartApp.service.register(userRegisterPanel1.getValue(),
						StartApp.callback);
			}
		}));
		root.add(new Label("Form Panel 2:"));
		root.add(userRegisterPanel2);
		root.add(new Button("Register 2:", new ClickHandler() {
			@Override
			public void onClick(ClickEvent arg0) {
				StartApp.service.register(userRegisterPanel2.getValue(),
						StartApp.callback);
			}
		}));
	}
}
