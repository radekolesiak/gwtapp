package org.gwtapp.core.client.html.core.api;

import org.gwtapp.core.client.html.ui.core.HWidget;

import com.google.gwt.user.client.rpc.RemoteService;

public interface HTMLService extends RemoteService {
	HWidget getWidget(String id);
}
