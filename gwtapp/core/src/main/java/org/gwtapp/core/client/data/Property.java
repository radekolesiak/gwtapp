package org.gwtapp.core.client.data;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface Property {
	@SuppressWarnings("unchecked")
	Class initBy() default Object.class;
}
