package org.gwtapp.extension.user.client.data;

import java.io.Serializable;

import org.gwtapp.core.rpc.data.ModelData;
import org.gwtapp.extension.user.client.data.metafield.EmailMetaField;
import org.gwtapp.extension.user.client.data.metafield.IdMetaField;
import org.gwtapp.extension.user.client.data.metafield.LoginMetaField;
import org.gwtapp.extension.user.client.data.metafield.NameMetaField;

public interface User extends ModelData, Serializable {

	public final static IdMetaField ID = new IdMetaField();
	public final static LoginMetaField LOGIN = new LoginMetaField();
	public final static EmailMetaField EMAIL = new EmailMetaField();
	public final static NameMetaField NAME = new NameMetaField();

	void setId(Long id);

	Long getId();

	void setLogin(String login);

	String getLogin();

	void setEmail(String email);

	String getEmail();

	void setName(String name);

	String getName();
}
