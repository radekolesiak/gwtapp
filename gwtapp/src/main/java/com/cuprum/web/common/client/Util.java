package com.cuprum.web.common.client;

import java.util.List;

import com.cuprum.web.smallapp.mainapp.client.i18n.InfoMessages;
import com.extjs.gxt.ui.client.widget.Info;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

public class Util {
	private static boolean isDebug = false;

	public static void enableDebug() {
		isDebug = true;
	}

	public static void disableDebug() {
		isDebug = true;
	}

	public static boolean isDebug() {
		return isDebug;
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

	public static boolean isToUpdate(long t0, long t1) {
		return t1 - t0 >= Math.min(Constants.USER_SESSION_TIME_OUT,
				Constants.UPDATE_FREQUENCY);
	}

	public static Widget getWidget(Widget widget) {
		if (isNotNull(widget)) {
			return widget;
		} else {
			return new Label();
		}
	}

	public static void showOnFailureCallbackDefault() {
		InfoMessages messages = GWT.create(InfoMessages.class);
		Info.display(messages.msgOnFailureTitle(), messages.msgOnFailure(),
				new String[] {});
	}
}
