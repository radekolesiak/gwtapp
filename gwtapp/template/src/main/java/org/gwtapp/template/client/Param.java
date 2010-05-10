package org.gwtapp.template.client;

public class Param {
	
	private String name;
	private String value;

	public Param() {
		this("", "");
	}

	public Param(String name, String value) {
		setName(name);
		setValue(value);
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
}
