package org.gwtapp.template.client;

import java.util.Map;

import com.google.gwt.core.client.JavaScriptObject;

public class TemplateUtils {

	public static native String replaceParameters(String s,
			JavaScriptObject array)/*-{
									return s.replace(
									/(\{[1-9]\d*\})/g,  
									function($1){
										if($1 && array[$1]){
											return(array[$1]);
										} else {
											return("");
										}
									}
									)
									}-*/;

	public static native String replaceTemplate(String template, String regexp,
			JavaScriptObject array)/*-{
									return template.replace(
									new RegExp(regexp, "gi"),  
									function($1){
										if($1 && array[$1]){
											return("id=\""+array[$1]+"\"");
										} else {
											return("");
										}
									}
									)
									}-*/;

	public static String replaceTemplate(String template,
			Map<String, String> ids) {
		String regexp = "";
		for (String name : ids.keySet()) {
			if (!regexp.isEmpty()) {
				regexp += "|";
			}
			regexp += "(t:template=\"" + name + "\")";
		}
		JavaScriptObject array = createArray();
		for (Map.Entry<String, ?> entry : ids.entrySet()) {
			addToArray(array, "t:template=\"" + entry.getKey() + "\"", entry
					.getValue()
					+ "");
		}
		return replaceTemplate(template, regexp, array);
	}

	public static String replaceParameters(String s, String... params) {
		JavaScriptObject array = createArray();
		for (int i = 0; i < params.length; i++) {
			addToArray(array, "{" + (i + 1) + "}", params[i]);
		}
		return replaceParameters(s, array);
	}

	public static native JavaScriptObject createArray() /*-{
														var array = new Array();
														return array;
														}-*/;

	public static native void addToArray(JavaScriptObject array, String key,
			Object o) /*-{
						array[key] = o;
						}-*/;
}
