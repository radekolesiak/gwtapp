package org.gwtapp.rest;

public interface RestEngine {
	String process(String methodName, String xmlArgs);
	String onFailure(String methodName, Exception e);
	String onNull(String methodName);
}
