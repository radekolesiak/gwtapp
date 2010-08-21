package org.gwtapp.extension.user.client.data;

import org.gwtapp.extension.user.client.data.metafield.PasswordMetaField;
import org.gwtapp.extension.user.client.data.metafield.PasswordVerifyMetaField;

public interface UserPassword extends User {

	public final static PasswordMetaField PASSWORD = new PasswordMetaField();
	public final static PasswordVerifyMetaField PASSWORD_VERIFY = new PasswordVerifyMetaField();

	void setPassword(String password);

	String getPassword();

	void setPasswordVerify(String passwordVerify);

	String getPasswordVerify();
}
