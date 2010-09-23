package org.gwtapp.extension.user.client;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import org.gwtapp.extension.user.client.data.User;
import org.gwtapp.form.client.ui.TemplateModelPanel;
import org.gwtapp.template.client.handler.TextBoxHandler;

import com.google.inject.BindingAnnotation;
import com.google.inject.Inject;

public class UserPanel extends TemplateModelPanel<User> {

	@Retention(RetentionPolicy.RUNTIME)
	@BindingAnnotation
	public static @interface Bind {
	}

	@Inject
	public UserPanel(@Bind TemplateCallback callback) {
		super(callback);
		add(User.LOGIN, new TextBoxHandler());
		add(User.EMAIL, new TextBoxHandler());
	}
}
