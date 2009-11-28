package org.gwtapp.startapp.client.data.user.register;

import java.util.List;

import org.gwtapp.core.client.data.AutoField;
import org.gwtapp.startapp.client.data.user.register.autofields.EmailAutoField;
import org.gwtapp.startapp.client.data.user.register.autofields.LoginAutoField;
import org.gwtapp.startapp.client.data.user.register.autofields.PasswordAutoField;
import org.gwtapp.startapp.client.data.user.register.autofields.TockensAutoField;

import com.google.gwt.user.client.rpc.IsSerializable;

public interface UserRegister extends IsSerializable {

	public final static AutoField<UserRegister, String> LOGIN = new LoginAutoField();
	public final static AutoField<UserRegister, String> PASSWORD = new PasswordAutoField();
	public final static AutoField<UserRegister, String> EMAIL = new EmailAutoField();
	public final static AutoField<UserRegister, List<String>> TOCKENS = new TockensAutoField();

	void setLogin(String login);

	String getLogin();

	void setPassword(String password);

	String getPassword();

	void setEmail(String email);

	String getEmail();

	void setTockens(List<String> tockens);

	List<String> getTockens();

}
