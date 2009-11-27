package org.gwtapp.startapp.client.data;

import java.util.List;

import org.gwtapp.core.client.data.AutoField;
import org.gwtapp.core.client.data.ModelData;

public interface UserRegisterModel extends UserRegister, ModelData {

	public final static AutoField<UserRegisterModel> LOGIN_FIELD = new AutoField<UserRegisterModel>() {
		@Override
		public String name() {
			return UserRegister.LOGIN;
		}

		@Override
		public Object get(UserRegisterModel model) {
			return model.getLogin();
		}

		@Override
		public void set(UserRegisterModel model, Object value) {
			model.setLogin((String) value);
		}
	};


	public final static AutoField<UserRegisterModel> EMAIL_FIELD = new AutoField<UserRegisterModel>() {
		@Override
		public String name() {
			return UserRegister.EMAIL;
		}

		@Override
		public Object get(UserRegisterModel model) {
			return model.getEmail();
		}

		@Override
		public void set(UserRegisterModel model, Object value) {
			model.setEmail((String) value);
		}
	};

	public final static AutoField<UserRegisterModel> PASSWORD_FIELD = new AutoField<UserRegisterModel>() {
		@Override
		public String name() {
			return UserRegister.PASSWORD;
		}

		@Override
		public Object get(UserRegisterModel model) {
			return model.getPassword();
		}

		@Override
		public void set(UserRegisterModel model, Object value) {
			model.setPassword((String) value);
		}
	};
	

	public final static AutoField<UserRegisterModel> TOCKENS_FIELD = new AutoField<UserRegisterModel>() {
		@Override
		public String name() {
			return UserRegister.TOCKENS;
		}

		@Override
		public Object get(UserRegisterModel model) {
			return model.getTockens();
		}

		@SuppressWarnings("unchecked")
		@Override
		public void set(UserRegisterModel model, Object value) {
			model.setTockens((List<String>) value);
		}
	};

}
