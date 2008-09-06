package com.cuprum.server.common.utils;

public enum Properties {
	REGISTRATION_MAIL_SMTP_SERVER("smtp_server"),
	REGISTRATION_MAIL_SMTP_AUTH("smtp_auth"),
	REGISTRATION_MAIL_SMTP_USER("smtp_user"),
	REGISTRATION_MAIL_SMTP_PASSWORD("smtp_password");
	
	private String property;

	private Properties(final String property) {
		this.property = property;
	}

	public String get() {
		return property;
	}
}
