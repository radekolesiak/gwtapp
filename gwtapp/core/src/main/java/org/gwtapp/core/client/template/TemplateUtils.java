package org.gwtapp.core.client.template;

import java.util.Map;

import org.gwtapp.core.client.Utils;

import com.google.gwt.core.client.JavaScriptObject;

public class TemplateUtils {

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
			regexp += "(template=\"" + name + "\")";
		}
		JavaScriptObject array = Utils.createArray();
		for (Map.Entry<String, ?> entry : ids.entrySet()) {
			Utils.addToArray(array, "template=\"" + entry.getKey() + "\"",
					entry.getValue() + "");
		}
		return replaceTemplate(template, regexp, array);
	}
}
