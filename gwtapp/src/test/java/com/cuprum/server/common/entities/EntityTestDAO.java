package com.cuprum.server.common.entities;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;

import com.cuprum.server.common.model.Model;
import com.cuprum.server.common.utils.DAO;
import com.cuprum.server.common.utils.HibernateDatabases;
import com.cuprum.server.common.utils.IDAO;
import com.cuprum.utils.ToTestHibernateDatabases;

public class EntityTestDAO extends Model {
	/**
	 * Logger for this class.
	 */
	static final Logger LOGGER = Logger.getLogger(EntityTestDAO.class);

	private IDAO dao = null;

	public IDAO getDAO() {
		return dao;
	}

	public HibernateDatabases getDatabase() {
		return ToTestHibernateDatabases.TEST;
	}

	/**
	 * Sets some stuff before unit test calling.
	 */
	@Before
	public void setUp() throws Exception {
		LOGGER.debug("setUp");

		dao = new DAO();
		dao.setupContext(getDatabase());
		open();
	}

	/**
	 * Sets some stuff after unit test calling.
	 */
	@After
	public void tearDown() throws Exception {
		close();
		dao.close();
		dao = null;
		LOGGER.debug("tearDown\n");
	}

	EntityTestDAO bean = null;

	protected EntityTestDAO getBean() {
		return bean;
	}

	public void open() {
		close();
		bean = (EntityTestDAO) dao.getBean(EntityTestDAO.class);
		// bean.pushTX();
	}

	public void close() {
		if (bean != null) {
			// bean.popTX();
			bean.close();
		}
		bean = null;
	}

	protected void restart() {
		close();
		open();
	}
}
