package org.gwtapp.core.client.html.core.api;

import java.io.Serializable;

import com.google.gwt.user.client.rpc.IsSerializable;
import com.google.gwt.user.client.rpc.RemoteService;

public interface HTMLService extends RemoteService {
	IsSerializable getResponseIsSerializable();
	Serializable getResponseSerializable();
}
