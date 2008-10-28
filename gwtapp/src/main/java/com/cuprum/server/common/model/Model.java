package com.cuprum.server.common.model;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

import org.hibernate.Transaction;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.cuprum.server.common.utils.IDAO;

public abstract class Model extends HibernateDaoSupport implements IModel {
	private LinkedList<Transaction> transactions = new LinkedList<Transaction>();

	private IDAO<IModel> dao;

	public void pushTX() {
		transactions.add(getSession().beginTransaction());
	}

	public Transaction getTX() {
		return transactions.getLast();
	}

	public void popTX() {
		if (transactions.size() > 0) {
			Transaction transaction = transactions.removeLast();
			if (transaction != null) {
				transaction.commit();
			}
		}
	}

	/**
	 * You must add try/catch section between pushTX() and popTX() calls and
	 * call this method in the catch section. <code>
	 * pushTX();
	 * try {
	 * ...
	 * } catch (Exception e) {
	 *  exceptionTX();
	 *  throw e;
	 * }
	 * popTX();
	 * </code>
	 */
	public void exceptionTX() {
		if (transactions.size() > 0) {
			Transaction transaction = transactions.removeLast();
			if (transaction != null) {
				transaction.rollback();
			}
		}
	}

	@SuppressWarnings("unchecked")
	public <T> T execute(HibernateCallback callback) {
		return (T) getHibernateTemplate().execute(callback);
	}

	@SuppressWarnings("unchecked")
	public <T> T save(Object object) {
		return (T) getHibernateTemplate().save(object);
	}

	public <T> T saveAndGet(Class<T> c, Object object) {
		return get(c, (Long) getHibernateTemplate().save(object));
	}

	public void saveOrUpdate(Object object) {
		getHibernateTemplate().saveOrUpdate(object);
	}

	public void saveOrIgnore(Object object) {
		try {
			getHibernateTemplate().save(object);
		} catch (DataIntegrityViolationException e) {
			if (e.getCause() instanceof ConstraintViolationException) {
			} else {
				throw e;
			}
		}
	}

	public void update(Object object) {
		getHibernateTemplate().update(object);
	}

	public void delete(Object object) {
		getHibernateTemplate().delete(object);
	}

	@SuppressWarnings("unchecked")
	public List createQuery(String query) {
		return getHibernateTemplate().find(query);
	}

	public boolean contains(Object object) {
		return getHibernateTemplate().contains(object);
	}

	@SuppressWarnings("unchecked")
	public <T> T load(Class<T> c, Serializable s) {
		return (T) getHibernateTemplate().load(c, s);
	}

	@SuppressWarnings("unchecked")
	public <T> T get(Class<T> c, Serializable s) {
		return (T) getHibernateTemplate().get(c, s);
	}

	/**
	 * @param dao
	 *            the dao to set
	 */
	public void setDAO(IDAO<IModel> dao) {
		this.dao = dao;
	}

	/**
	 * @return the dao
	 */
	public IDAO<IModel> getDAO() {
		return dao;
	}
}
