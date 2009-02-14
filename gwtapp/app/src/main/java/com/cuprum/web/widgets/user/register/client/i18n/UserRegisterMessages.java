package com.cuprum.web.widgets.user.register.client.i18n;

import com.google.gwt.i18n.client.Messages;

public interface UserRegisterMessages extends Messages {
	String msgHeading();

	String msgLoginLabel();

	String msgLoginEmptyText();

	String msgPasswordLabel();

	String msgPasswordConfirmLabel();

	String msgMailLabel();

	String msgMailConfirmLabel();

	String msgMailEmptyText();

	String msgSubmit();

	String msgLoginTooShort(int min);

	String msgLoginAlreadyExists();

	String msgLoginFormat();

	String msgPasswordTooShort(int min);

	String msgPasswordsDifferent();

	String msgMailAlreadyExists();

	String msgMailFormat();

	String msgMailsDifferent();
}
