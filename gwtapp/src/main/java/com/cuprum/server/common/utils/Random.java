package com.cuprum.server.common.utils;

public class Random {
	public static final String RANDS = "abcdefghijklmnopqrstuwxyz0123456789";

	public static final int UID_LENGTH = 16;

	public static String getUid() {
		String uid = "";
		for (int i = 0; i < UID_LENGTH; i++) {
			uid += RANDS.charAt((int) (Math.random() * RANDS.length()));
		}
		return uid;
	}
}
