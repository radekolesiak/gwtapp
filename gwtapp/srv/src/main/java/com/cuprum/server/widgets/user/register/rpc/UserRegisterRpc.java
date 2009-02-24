package com.cuprum.server.widgets.user.register.rpc;

import com.cuprum.server.common.entities.Property;
import com.cuprum.server.common.entities.UserConfirm;
import com.cuprum.server.common.model.property.IPropertyModel;
import com.cuprum.server.common.model.user.IUserRegisterModel;
import com.cuprum.server.common.rpc.RemoteServiceSession;
import com.cuprum.server.common.utils.Mail;
//import com.cuprum.web.widgets.user.register.client.UserRegister;
import com.cuprum.web.widgets.user.register.client.data.TUserRegisterValue;
import com.cuprum.web.widgets.user.register.client.stub.IUserRegister;
import com.cuprum.web.widgets.user.register.properties.Properties;

public class UserRegisterRpc extends RemoteServiceSession implements
		IUserRegister {

	/**
	 * UID.
	 */
	private static final long serialVersionUID = 5230394229602005082L;

	public TUserRegisterValue processUserRegister(
			TUserRegisterValue userRegister) {

		UserConfirm confirm = getBean(IUserRegisterModel.class).register(
				userRegister);

		Property confirmUrl = getBean(IPropertyModel.class).get(
				Properties.CONFIRM_URL);

		if (!userRegister.hasErrors() && confirm != null) {
			String smtp = getBean(IPropertyModel.class)
					.get(
							com.cuprum.server.common.properties.Properties.MAIL_SMTP_SERVER)
					.getValue();
			String noreply = getBean(IPropertyModel.class)
					.get(
							com.cuprum.server.common.properties.Properties.MAIL_USER_NOREPLY)
					.getValue();

			Mail mail = new Mail();
			mail.setSmtp(smtp);
			mail.setMailFrom(noreply);
			mail.setRecipientTo(userRegister.mail.value);
			mail.setSubject("Confirm registration.");
			mail.setContent(confirmUrl + "?" + IUserRegister.CONFIRM_REQUEST+ "=" + confirm.getUid());
			mail.start();
		}

		return userRegister;
	}

	public Boolean confirm(String uid) {
		return getBean(IUserRegisterModel.class).confirm(uid);
	}
}
