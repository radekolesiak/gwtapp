package org.gwtapp.core.client.api;

import org.gwtapp.core.client.data.B;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("a.rpc")
public interface AService extends RemoteService {

	B getB();

	boolean setB(B b);
}
