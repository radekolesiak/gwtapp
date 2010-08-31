package org.gwtapp.startapp.client;

import java.util.List;

import org.gwtapp.core.client.AsyncCallbackInjector;
import org.gwtapp.core.rpc.data.ProviderAdapter;
import org.gwtapp.extension.user.client.ReCaptchaUserRegisterPanel;
import org.gwtapp.extension.user.client.UserPanel;
import org.gwtapp.extension.user.client.data.ReCaptchaUser;
import org.gwtapp.extension.user.client.data.ReCaptchaUserImpl;
import org.gwtapp.extension.user.client.data.User;
import org.gwtapp.extension.user.client.data.UserImpl;
import org.gwtapp.extension.user.client.data.UserPassword;
import org.gwtapp.extension.user.client.data.UserPasswordImpl;
import org.gwtapp.extension.widget.client.ui.template.ListPanel;
import org.gwtapp.startapp.client.template.ListPanelTemplateCallback;
import org.gwtapp.startapp.client.template.ReCaptchaUserRegisterTemplateCallback;
import org.gwtapp.startapp.client.template.UserPanelTemplateCallback;
import org.gwtapp.startapp.client.test.ListTestPanel;
import org.gwtapp.startapp.client.test.ListTestPanel.Item;
import org.gwtapp.template.client.ui.TemplatePanel.TemplateCallback;

import com.google.gwt.inject.client.AbstractGinModule;
import com.google.gwt.user.client.ui.ListBox;
import com.google.inject.TypeLiteral;

public class ClientModule extends AbstractGinModule {

	public static class ListPanelFieldName extends ProviderAdapter<String> {
		public ListPanelFieldName() {
			super("MyListBoxField");
		}
	}

	@Override
	protected void configure() {
		bind(AsyncCallbackInjector.class)
				.to(AsyncCallbackInjectorAdapter.class);

		bind(User.class).to(UserImpl.class);
		bind(UserPassword.class).to(UserPasswordImpl.class);
		bind(ReCaptchaUser.class).to(ReCaptchaUserImpl.class);

		bind(UserPanel.class).annotatedWith(
				ReCaptchaUserRegisterPanel.AUserPanel.class)
				.to(UserPanel.class);

		bind(ListBox.class).annotatedWith(ListPanel.AListBox.class).to(
				ListBox.class);

		bind(String.class).annotatedWith(ListPanel.AFieldName.class)
				.toProvider(ListPanelFieldName.class);

		bind(new TypeLiteral<ListPanel.Formatter<ListTestPanel.Item>>() {
		}).annotatedWith(ListPanel.AFormatter.class).to(
				ListTestPanel.Formatter.class);

		bind(new TypeLiteral<List<Item>>() {
		}).annotatedWith(ListPanel.AItems.class).toProvider(
				ListTestPanel.ItemsProvider.class);

		bind(TemplateCallback.class).annotatedWith(
				ReCaptchaUserRegisterPanel.ATemplateCallback.class).to(
				ReCaptchaUserRegisterTemplateCallback.class);

		bind(TemplateCallback.class).annotatedWith(
				UserPanel.ATemplateCallback.class).to(
				UserPanelTemplateCallback.class);

		bind(TemplateCallback.class).annotatedWith(
				ListPanel.ATemplateCallback.class).to(
				ListPanelTemplateCallback.class);
	}
}
