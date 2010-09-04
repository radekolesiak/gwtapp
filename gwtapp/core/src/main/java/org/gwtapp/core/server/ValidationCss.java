package org.gwtapp.core.server;

import org.gwtapp.core.rpc.exception.ValidationException;

interface ValidationCss {

	void setValidationClass(Class<? extends ValidationException> validationClass);

	Class<? extends ValidationException> getValidationClass();

	void setPrefix(String prefix);

	String getPrefix();

	void setSeparator(String separator);

	String getSeparator();

	void setStyle(String style);

	String getStyle();

	String getCSS();
}
