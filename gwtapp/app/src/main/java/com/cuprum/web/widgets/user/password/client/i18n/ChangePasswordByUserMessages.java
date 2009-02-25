package com.cuprum.web.widgets.user.password.client.i18n;

import com.google.gwt.i18n.client.Messages;

public interface ChangePasswordByUserMessages extends Messages {
	String msgHeading();

	String msgOldPasswordLabel();

	String msgInvalidOldPasswordLabel();

	String msgNewPasswordLabel();

	String msgNewPasswordConfirmLabel();
	
	String msgNewPasswordsDifferent();
	
	String msgNewPasswordTooShort(int min);

	String msgSubmit();
}