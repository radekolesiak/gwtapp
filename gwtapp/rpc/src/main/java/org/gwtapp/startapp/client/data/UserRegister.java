package org.gwtapp.startapp.client.data;

import java.util.ArrayList;
import java.util.List;

import org.gwtapp.core.client.data.AutoField;
import org.gwtapp.core.client.data.AutoFieldAdapter;

import com.google.gwt.user.client.rpc.IsSerializable;

public interface UserRegister extends IsSerializable {

	public final static AutoField<UserRegister, String> LOGIN = new AutoFieldAdapter<UserRegister, String>(
			"login") {
		@Override
		public Object get(UserRegister model) {
			return model.getLogin();
		}

		@Override
		public void set(UserRegister model, Object value) {
			model.setLogin((String) value);
		}
	};

	public final static AutoField<UserRegister, String> EMAIL = new AutoFieldAdapter<UserRegister, String>(
			"email") {
		@Override
		public Object get(UserRegister model) {
			return model.getEmail();
		}

		@Override
		public void set(UserRegister model, Object value) {
			model.setEmail((String) value);
		}
	};

	public final static AutoField<UserRegister, String> PASSWORD = new AutoFieldAdapter<UserRegister, String>(
			"password") {
		@Override
		public Object get(UserRegister model) {
			return model.getPassword();
		}

		@Override
		public void set(UserRegister model, Object value) {
			model.setPassword((String) value);
		}
	};

	public final static AutoField<UserRegister, List<String>> TOCKENS = new AutoFieldAdapter<UserRegister, List<String>>(
			"tockens") {

		@Override
		public Object get(UserRegister model) {
			return model.getTockens();
		}

		@SuppressWarnings("unchecked")
		@Override
		public void set(UserRegister model, Object value) {
			model.setTockens((List<String>) value);
		}

		@Override
		public List<String> def() {
			return new ArrayList<String>();
		}
	};

	void setLogin(String login);

	String getLogin();

	void setPassword(String password);

	String getPassword();

	void setEmail(String email);

	String getEmail();

	void setTockens(List<String> tockens);

	List<String> getTockens();

}
