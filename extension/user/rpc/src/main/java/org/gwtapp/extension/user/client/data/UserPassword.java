package org.gwtapp.extension.user.client.data;

import java.io.Serializable;

import org.gwtapp.core.rpc.data.ModelData;
import org.gwtapp.extension.user.client.data.metafield.password.IdMetaField;
import org.gwtapp.extension.user.client.data.metafield.password.PasswordMetaField;
import org.gwtapp.extension.user.client.data.metafield.password.UserMetaField;

public interface UserPassword extends ModelData, Serializable {

	public final static IdMetaField ID = new IdMetaField();
	public final static PasswordMetaField PASSWORD = new PasswordMetaField();
	public final static UserMetaField USER = new UserMetaField();

	void setId(Long id);

	Long getId();

	void setPassword(String password);

	String getPassword();

	void setUser(User user);

	User getUser();
}
