package org.gwtapp.core.client.html.core.api;

import com.google.gwt.user.client.rpc.IsSerializable;
import com.google.gwt.user.client.rpc.RemoteService;

public interface HTMLService extends RemoteService {
	IsSerializable getResponse();
}
