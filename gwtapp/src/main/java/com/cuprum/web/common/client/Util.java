package com.cuprum.web.common.client;

import java.util.List;

import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

public class Util {
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
	
	public static boolean isToUpdate(long t0, long t1) {
		return t1 - t0 >= Math.min(Constants.USER_SESSION_TIME_OUT, Constants.UPDATE_FREQUENCY);
	}
	
	public static Widget getWidget(Widget widget) {
		if(isNotNull(widget)){
			return widget;
		} else {
			return new Label();
		}
	}
}
