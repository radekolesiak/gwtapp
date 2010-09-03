package org.gwtapp.extension.user.client;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import org.gwtapp.core.client.ui.HasValidation;
import org.gwtapp.extension.user.client.data.User;
import org.gwtapp.extension.user.client.data.exception.UserValidationException;
import org.gwtapp.form.client.ui.TemplateModelPanel;
import org.gwtapp.template.client.handler.TextBoxHandler;
import org.gwtapp.template.client.handler.WidgetHandler;

import com.google.inject.BindingAnnotation;
import com.google.inject.Inject;

public class UserPanel extends TemplateModelPanel<User> implements
		HasValidation<UserValidationException> {

	@Retention(RetentionPolicy.RUNTIME)
	@BindingAnnotation
	public static @interface ATemplateCallback {
	}

	private final WidgetHandler validationLoginInvalid = new WidgetHandler();

	@Inject
	public UserPanel(@ATemplateCallback TemplateCallback callback) {
		super(callback);
		add(User.LOGIN, new TextBoxHandler());
		add(User.EMAIL, new TextBoxHandler());
		add(User.NAME, new TextBoxHandler());
		add("validation-login-invalid", validationLoginInvalid);
	}

	@Override
	public void setValidation(UserValidationException validation) {
		clearValidation();
		validationLoginInvalid.getWidget().addStyleName("validation-enabled");
	}

	@Override
	public void clearValidation() {
		validationLoginInvalid.getWidget().removeStyleName("validation-enabled");
	}
}
