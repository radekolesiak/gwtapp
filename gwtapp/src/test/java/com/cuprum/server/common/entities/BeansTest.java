package com.cuprum.server.common.entities;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class BeansTest extends EntityTestDAO {
	/**
	 * Inserts first user.
	 */
	@Test
	public final void isBeanAdressAlwaysTheSame() {
		EntityTestDAO bean1 = getDAO().getBean(EntityTestDAO.class);
		EntityTestDAO bean2 = getDAO().getBean(EntityTestDAO.class);
		assertTrue(bean1 == bean2);
	}

}
