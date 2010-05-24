package org.gwtapp.startapp.rpc.data.user.register;

import java.util.List;

import org.gwtapp.core.rpc.data.MetaField;
import org.gwtapp.startapp.rpc.data.user.register.metafields.EmailAutoField;
import org.gwtapp.startapp.rpc.data.user.register.metafields.LoginAutoField;
import org.gwtapp.startapp.rpc.data.user.register.metafields.PasswordAutoField;
import org.gwtapp.startapp.rpc.data.user.register.metafields.TockensAutoField;

import com.google.gwt.user.client.rpc.IsSerializable;

public interface UserRegister extends IsSerializable {

	public final static MetaField<UserRegister, String> LOGIN = new LoginAutoField();
	public final static MetaField<UserRegister, String> PASSWORD = new PasswordAutoField();
	public final static MetaField<UserRegister, String> EMAIL = new EmailAutoField();
	public final static MetaField<UserRegister, List<String>> TOCKENS = new TockensAutoField();

	void setLogin(String login);

	String getLogin();

	void setPassword(String password);

	String getPassword();

	void setEmail(String email);

	String getEmail();

	void setTockens(List<String> tockens);

	List<String> getTockens();

}
