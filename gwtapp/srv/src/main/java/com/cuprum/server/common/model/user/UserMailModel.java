package com.cuprum.server.common.model.user;

import com.cuprum.server.common.entities.User;
import com.cuprum.server.common.model.Model;
import com.cuprum.server.common.model.user.xql.XqlMail;
import com.cuprum.web.common.client.HasRegExp;
import com.cuprum.web.common.client.Util;
import com.cuprum.web.common.client.data.TDualValue;
import com.cuprum.web.common.client.exceptions.DualTextFieldInvalidException;
import com.cuprum.web.common.client.exceptions.RegExpException;
import com.cuprum.web.common.client.exceptions.model.user.MailAlreadyExistsException;

public class UserMailModel extends Model implements IUserMailModel {
	public boolean existsMail(User user) {
		return existsMail(user.getMail());
	}

	public boolean existsMail(String mail) {
		return Util.isNotNull(execute(new XqlMail(mail)));
	}

	public void verifyNewMail(TDualValue<String> mail) {
		if (!mail.value.equals(mail.second)) {
			mail.error = new DualTextFieldInvalidException();
		} else if (mail instanceof HasRegExp
				&& !((HasRegExp) mail).match(mail.value)) {
			mail.error = new RegExpException();
		} else if (existsMail(mail.value)) {
			mail.error = new MailAlreadyExistsException();
		}
	}
}
