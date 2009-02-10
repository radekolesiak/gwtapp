package com.cuprum.server.common.utils;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;

import com.cuprum.server.common.model.IModel;
import com.cuprum.server.common.model.Model;

public class EntityTestDAO extends Model {
	/**
	 * Logger for this class.
	 */
	static final Logger LOGGER = Logger.getLogger(EntityTestDAO.class);

	private IHibernateDAO<IModel> dao = null;

	public IDAO<IModel> getDAO() {
		return dao;
	}

	public HibernateDatabases getDatabase() {
		return DefaultTestHibernateDatabases.TEST;
	}

	static {
		org.hsqldb.Server.main(new String[] {});
	}

	/**
	 * Sets some stuff before unit test calling.
	 */
	@Before
	public void setUp() throws Exception {
		LOGGER.debug("setUp");

		dao = new HibernateDAO();
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
