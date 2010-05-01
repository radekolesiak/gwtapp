package org.gwtapp.startapp.client.ui;

import org.gwtapp.core.client.Utils;
import org.gwtapp.html.rpc.HClient;
import org.gwtapp.startapp.client.HWidgets;
import org.gwtapp.startapp.client.StartApp;
import org.gwtapp.startapp.client.ui.user.register.UserRegisterPanel;
import org.gwtapp.startapp.rpc.data.user.register.UserRegisterModel;
import org.gwtapp.startapp.rpc.ui.user.register.HUserRegisterPanel;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.i18n.client.Dictionary;
import com.google.gwt.user.client.rpc.SerializationException;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.ComplexPanel;
import com.google.gwt.user.client.ui.Label;

public class UserRegisterTabExt {

	private ComplexPanel root;

	private final Button btn1 = new Button("Register 1:");
	private final Button btn2 = new Button("Register 2:");

	public UserRegisterTabExt() {
		String serializedUserRegisterPanel = HClient.decode(Dictionary
				.getDictionary(HWidgets.DICTIONARY).get(
						HWidgets.HUserRegisterPanel));
		try {
			HUserRegisterPanel hUserRegisterPanel = (HUserRegisterPanel) HClient
					.getSerializedObject(serializedUserRegisterPanel);
			final UserRegisterPanel urp = new UserRegisterPanel(
					hUserRegisterPanel);
			root = Utils.updateToRootPanel(urp);

			final Button btn = new Button("Remove wrapped form");
			btn.addClickHandler(new ClickHandler() {
				@Override
				public void onClick(ClickEvent event) {
					btn1.setEnabled(false);
					btn.setEnabled(false);
					root.remove(urp);
				}
			});

			root.add(new Label("Form Panel 1:"));
			root.add(urp);
			root.add(btn1);
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
		btn1.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent arg0) {
				StartApp.service.register(userRegisterPanel1.getValue(),
						StartApp.callback);
			}
		});
		btn2.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent arg0) {
				StartApp.service.register(userRegisterPanel2.getValue(),
						StartApp.callback);
			}
		});
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
