package com.cuprum.web.common.client;

import java.util.List;

import com.google.gwt.user.client.Window;

public class Util {

	public static String getUrl() {
		return Window.Location.getProtocol() + "//" + Window.Location.getHost()
				+ Window.Location.getPath();
	}

	@SuppressWarnings("unchecked")
	public static <T> T getFirst(List list) {
		if (list == null || list.size() == 0) {
			return null;
		} else {
			return (T) list.get(0);
		}
	}

	public static boolean isNull(Object o) {
		return o == null;
	}

	public static boolean isNotNull(Object o) {
		return o != null;
	}

	public static boolean isUserSessionToUpdate(long t0, long t1) {
		return t1 - t0 >= Math.min(Constants.USER_SESSION_TIME_OUT,
				Constants.USER_SESSION_UPDATE_TIMER);
	}

	public static <T> boolean equals(T a, T b) {
		if (a == b) {
			return true;
		}

		if (a == null) {
			return false;
		}

		return a.equals(b);
	}

	public static <T extends HasEqualsT> boolean equalsT(T a, T b) {
		if (a == b) {
			return true;
		}

		if (a == null) {
			return false;
		}

		return a.equalsT(b);
	}
}
