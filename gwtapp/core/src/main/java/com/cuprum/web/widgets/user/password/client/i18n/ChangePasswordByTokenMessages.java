package com.cuprum.web.widgets.user.password.client.i18n;

import com.google.gwt.i18n.client.Messages;

public interface ChangePasswordByTokenMessages extends Messages {
	String msgHeading();

	String msgPasswordLabel();

	String msgPasswordConfirmLabel();

	String msgSubmit();

	String msgPasswordsDifferent();

	String msgPasswordTooShort(int min);
}
