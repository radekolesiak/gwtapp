package com.cuprum.server.common.properties;

/**
 * 
 * @author cuprum
 * 
 *         Properties stored in the database.
 * 
 */
public class Properties {
	// e.g. http://gwtapp.cuprum.biz:8080/smallapp-app/
	public final static String CONFIRM_URL = "confirmUrl";
	
	// e.g. http://gwtapp.cuprum.biz:8080/smallapp-app/
	public final static String REMIND_URL = "remindUrl";
	
	// e.g. smtp_server
	public final static String MAIL_SMTP_SERVER = "mail_smtp_server";
	
	// e.g. noreply@smtp_server
	public final static String MAIL_USER_NOREPLY = "mail_user_noreply";
	
	// e.g. http://gwtapp.cuprum.biz:8081/smallapp-srv/ or null
	public final static String PROXY = "proxy";
	
	//public final static String MAIL_SMTP_AUTH = "mail_smtp_auth";
	
	//public final static String MAIL_SMTP_USER = "mail_smtp_user";
	
	//public final static String MAIL_SMTP_PASSWORD = "mail_smtp_password";
}
