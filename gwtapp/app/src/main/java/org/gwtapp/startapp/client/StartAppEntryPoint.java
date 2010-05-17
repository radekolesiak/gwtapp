
package org.gwtapp.startapp.client;

import org.gwtapp.template.client.TemplateRepository;

import com.google.gwt.core.client.EntryPoint;


public class StartAppEntryPoint implements EntryPoint {

	public final static TemplateRepository templates = new TemplateRepository(
			"/templates/");
	
	@Override
	public final void onModuleLoad() {
		onStartAppModuleLoad();
	}

	public void onStartAppModuleLoad() {
		
	}

}
