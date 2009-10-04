package org.gwtapp.startapp.client.data;

import java.util.ArrayList;
import java.util.List;

import org.gwtapp.core.client.data.Property;

import com.google.gwt.user.client.rpc.IsSerializable;

@SuppressWarnings("unchecked")
public interface UserRegister extends IsSerializable {

	public final static String LOGIN = "login";
	public final static String EMAIL = "email";
	public final static String PASSWORD = "password";
	public final static String TOCKENS = "tockens";

	void setLogin(String login);

	String getLogin();

	void setPassword(String password);

	String getPassword();

	void setEmail(String email);

	String getEmail();

	void setTockens(List tockens);

	@Property(initBy = ArrayList.class)
	List getTockens();
}
