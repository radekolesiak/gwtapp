package org.gwtapp.extension.user.client.api;

import org.gwtapp.core.rpc.exception.RpcException;
import org.gwtapp.extension.user.client.data.ReCaptchaUser;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("extension.recaptchauser.rpc")
public interface ReCaptchaUserService extends RemoteService {
	long addReCaptchaUser(ReCaptchaUser user) throws RpcException;
}
