package org.gwtapp.startapp.client.data;

import org.gwtapp.core.client.data.HashModelData;

public class UserRegisterModelImpl extends HashModelData implements
		UserRegisterModel {
	
	@SuppressWarnings("unchecked")
	public UserRegisterModelImpl() {
		super();
		{
			java.lang.Object o = get("tockens");
			if (o == null) {
				try {
					set("tockens", new java.util.ArrayList());
				} catch (Exception e) {
				}
			}
		}
	}

	public void setPassword(java.lang.String value) {
		set("password", value);
	}

	public java.lang.String getLogin() {
		return (java.lang.String) get("login");
	}

	public void setLogin(java.lang.String value) {
		set("login", value);
	}

	public void setEmail(java.lang.String value) {
		set("email", value);
	}

	public java.lang.String getEmail() {
		return (java.lang.String) get("email");
	}

	@SuppressWarnings("unchecked")
	public void setTockens(java.util.List value) {
		set("tockens", value);
	}

	@SuppressWarnings("unchecked")
	public java.util.List getTockens() {
		return (java.util.List) get("tockens");
	}

	public java.lang.String getPassword() {
		return (java.lang.String) get("password");
	}

	public java.util.Collection<java.lang.String> getPropertyNames() {
		return java.util.Arrays.asList(new java.lang.String[] { "email",
				"login", "password", "tockens" });
	}

}
