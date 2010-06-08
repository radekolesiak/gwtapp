package org.gwtapp.startapp.rpc.api;

import org.gwtapp.startapp.rpc.data.user.register.UserRegisterModel;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("download.rpc")
public interface DownloadService extends RemoteService {
	void download(UserRegisterModel userRegister);
}
