package com.cuprum.server.common.model;

import java.io.Serializable;
import java.util.List;

import org.springframework.orm.hibernate3.HibernateCallback;

import com.cuprum.server.common.utils.IDAO;

public interface IModel {
	<T> T execute(HibernateCallback callback);

	<T> T save(Object object);

	void saveOrUpdate(Object object);

	void saveOrIgnore(Object object);

	<T, R> T saveOnDuplicateUpdate(Class<T> c, IOnDuplicateUpdate<T> callback);

	<T> T saveAndGet(Class<T> c, Object object);

	void update(Object object);

	void delete(Object object);

	<T> List<T> createQuery(String query);

	boolean contains(Object object);

	<T> T load(Class<T> c, Serializable s);

	<T> T get(Class<T> c, Serializable s);

	void setDAO(IDAO<IModel> dao);

	IDAO<IModel> getDAO();
}
