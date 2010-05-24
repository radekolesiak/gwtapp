package org.gwtapp.startapp.rpc.data.user.register;

import java.util.List;

import org.gwtapp.core.rpc.data.MetaField;
import org.gwtapp.startapp.rpc.data.user.register.metafields.EmailMetaField;
import org.gwtapp.startapp.rpc.data.user.register.metafields.LoginMetaField;
import org.gwtapp.startapp.rpc.data.user.register.metafields.PasswordMetaField;
import org.gwtapp.startapp.rpc.data.user.register.metafields.TockensMetaField;

import com.google.gwt.user.client.rpc.IsSerializable;

public interface UserRegister extends IsSerializable {

	public final static MetaField<UserRegister, String> LOGIN = new LoginMetaField();
	public final static MetaField<UserRegister, String> PASSWORD = new PasswordMetaField();
	public final static MetaField<UserRegister, String> EMAIL = new EmailMetaField();
	public final static MetaField<UserRegister, List<String>> TOCKENS = new TockensMetaField();

	void setLogin(String login);

	String getLogin();

	void setPassword(String password);

	String getPassword();

	void setEmail(String email);

	String getEmail();

	void setTockens(List<String> tockens);

	List<String> getTockens();

}
