package org.gwtapp.startapp.client.api;

import org.gwtapp.startapp.client.data.user.register.UserRegister;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("gwtapp-download.rpc")
public interface DownloadService extends RemoteService {
	void download(UserRegister userRegister);
}
