package com.cuprum.web.widgets.user.register.rpc;

import com.cuprum.server.common.entities.Property;
import com.cuprum.server.common.entities.UserConfirm;
import com.cuprum.server.common.model.property.PropertyModel;
import com.cuprum.server.common.model.user.UserRegisterModel;
import com.cuprum.server.common.utils.Mail;
import com.cuprum.web.common.rpc.RemoteServiceServletSession;
import com.cuprum.web.widgets.user.register.client.UserRegister;
import com.cuprum.web.widgets.user.register.client.data.TUserRegisterValue;
import com.cuprum.web.widgets.user.register.client.stub.IUserRegister;
import com.cuprum.web.widgets.user.register.properties.Properties;

public class UserRegisterRpc extends RemoteServiceServletSession implements
		IUserRegister {

	/**
	 * UID.
	 */
	private static final long serialVersionUID = 5230394229602005082L;

	public TUserRegisterValue processUserRegister(
			TUserRegisterValue userRegister) {

		UserConfirm confirm = getBean(UserRegisterModel.class).register(
				userRegister);

		Property confirmUrl = getBean(PropertyModel.class).get(
				Properties.CONFIRM_URL);

		if (!userRegister.hasErrors() && confirm != null) {
			String smtp = getBean(PropertyModel.class)
					.get(
							com.cuprum.server.common.properties.Properties.MAIL_SMTP_SERVER)
					.getValue();
			String noreply = getBean(PropertyModel.class)
					.get(
							com.cuprum.server.common.properties.Properties.MAIL_USER_NOREPLY)
					.getValue();

			Mail mail = new Mail();
			mail.setSmtp(smtp);
			mail.setMailFrom(noreply);
			mail.setRecipientTo(userRegister.mail.value);
			mail.setSubject("Confirm registration.");
			mail.setContent(confirmUrl + "?" + UserRegister.CONFIRM_REQUEST
					+ "=" + confirm.getUid());
			mail.start();
		}

		return userRegister;
	}

	public Boolean confirm(String uid) {
		return getBean(UserRegisterModel.class).confirm(uid);
	}
}
