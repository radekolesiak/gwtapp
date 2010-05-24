package org.gwtapp.startapp.rpc.data.user.register.metafields;

import java.util.ArrayList;
import java.util.List;

import org.gwtapp.core.rpc.data.MetaFieldAdapter;
import org.gwtapp.startapp.rpc.data.user.register.UserRegister;

public class TockensMetaField extends MetaFieldAdapter<UserRegister, List<String>> {

	public TockensMetaField() {
		this("tockens");
	}

	public TockensMetaField(String name) {
		super(name);
	}

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

}
