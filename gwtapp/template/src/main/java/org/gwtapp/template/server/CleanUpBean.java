package org.gwtapp.template.server;

import org.apache.commons.lang.StringUtils;

public class CleanUpBean {

	private String text;

	public void setText(String text) {
		this.text = text;
	}

	public String getText() {
		if (text == null) {
			return null;
		} else {
			return StringUtils.replaceChars(StringUtils.trimToEmpty(text),
					"\r\n", "");
		}
	}
}
